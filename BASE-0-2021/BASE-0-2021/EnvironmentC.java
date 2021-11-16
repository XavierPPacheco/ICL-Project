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
    private PrintStream frame_stream;
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

        frame_stream = new PrintStream(new File("frame_" + depth + ".j"));
        frame_stream.println(".class public frame_" + depth);
        frame_stream.println(".super java/lang/Object");
        frame_stream.println(".field public sl " + getPrevFrame());

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
        frame_stream.close();
        // end emit
        codeBlock.emit("aload_3");
        codeBlock.emit("getfield frame_" + depth + "/sl " + getPrevFrame());
        codeBlock.emit("astore_3");
        return ancestor;
    }

    public void assoc(String id) {
        // add to frame
        frame_stream.println(".field public " + id + " I");
        // emit assoc
        codeBlock.emit("putfield frame_" + depth + "/" + id + " I");
        scope.add(id);
    }

    public int find(String id) {
        if (scope.contains(id))
            codeBlock.emit("getfield frame_" + depth + "/" + id + " I");
        else {
            codeBlock.emit("getfield frame_" + depth + "/sl Lframe_" + ancestor.getDepth() + ";");
            ancestor.find(id);
        }
        return 0;
    }

    private int getDepth() {
        return depth;
    }

    private String getPrevFrame() {
        return ancestor.getDepth() == -1 ? "Ljava/lang/Object;" : "Lframe_" + ancestor.getDepth() + ";";
    }

    private void defaultEnd() {
        frame_stream.println(".method public <init>()V");
        frame_stream.println("\taload_0");
        frame_stream.println("\tinvokenonvirtual java/lang/Object/<init>()V");
        frame_stream.println("\treturn");
        frame_stream.println(".end method");
    }
}
