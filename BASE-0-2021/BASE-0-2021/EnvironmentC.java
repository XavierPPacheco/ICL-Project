import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class EnvironmentC {

    private EnvironmentC ancestor;
    private CodeBlock codeBlock;
    private int depth;
    private Set<String> scope;
    private PrintStream frameStream;
    private static int envCounter = 0;


    public EnvironmentC(CodeBlock codeBlock) {
        this.codeBlock = codeBlock;
        this.ancestor = null;
        this.depth = -1;
        scope = new HashSet<>();
    }

    public EnvironmentC(CodeBlock codeBlock, EnvironmentC ancestor) throws IOException {
        this.codeBlock = codeBlock;
        this.ancestor = ancestor;
        this.depth = envCounter++;
        scope = new HashSet<>();

        // init new frame
        ICLCompiler.frames_to_compile.append("java -jar jasmin.jar frame_" + depth+".j\n");

        frameStream = new PrintStream(new File("frame_" + depth + ".j"));
        frameStream.println(".class public frame_" + depth);
        frameStream.println(".super java/lang/Object");
        frameStream.println(".field public sl " + getPrevFrame());

        // init emit
        codeBlock.emit("new frame_" + depth);
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial frame_" + depth + "/<init>()V");
        codeBlock.emit("dup");
        codeBlock.emit("aload_3");
        codeBlock.emit("putfield frame_" + depth + "/sl " + getPrevFrame());
        codeBlock.emit("astore_3");

    }

    EnvironmentC beginScope() {
        try {
            return new EnvironmentC(codeBlock, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    EnvironmentC endScope() {
        // end the frame
        defaultEnd();
        frameStream.close();
        // end emit
        codeBlock.emit("aload_3");
        codeBlock.emit("getfield frame_" + depth + "/sl " + getPrevFrame());
        codeBlock.emit("astore_3");
        return ancestor;
    }

    public void assoc(String id, IType type) {
        // emit assoc and add variable to frame
        if (type instanceof TypeRef) {
            codeBlock.emit("putfield frame_" + depth + "/" + id + " Ljava/lang/Object;");
            frameStream.println(".field public " + id + " Ljava/lang/Object;");
        } else {
            codeBlock.emit("putfield frame_" + depth + "/" + id + " I");
            frameStream.println(".field public " + id + " I");
        }
        scope.add(id);
    }

    public int find(String id, IType type) {
        if (scope.contains(id) && type instanceof TypeRef) {
            codeBlock.emit("getfield frame_" + depth + "/" + id + " " + "Ljava/lang/Object;");
        } else if (scope.contains(id) && !(type instanceof TypeRef)) {
            codeBlock.emit("getfield frame_" + depth + "/" + id + " I");
        } else {
            codeBlock.emit("getfield frame_" + depth + "/sl Lframe_" + ancestor.getDepth() + ";");
            ancestor.find(id, type);
        }
        return 0;
    }

    private int getDepth() {
        return depth;
    }

    private String getPrevFrame() {
        if(ancestor.getDepth() == -1 )
            return "Ljava/lang/Object;" ;
        return "Lframe_" + ancestor.getDepth() + ";";
    }

    private void defaultEnd() {
        frameStream.println(".method public <init>()V");
        frameStream.println("\taload_0");
        frameStream.println("\tinvokenonvirtual java/lang/Object/<init>()V");
        frameStream.println("\treturn");
        frameStream.println(".end method");
    }
}
