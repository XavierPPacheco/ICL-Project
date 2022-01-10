import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class ASTRef implements ASTNode {

    ASTNode v;

    public ASTRef(ASTNode t) {
        v = t;
    }

    @Override
    public IValue eval(Environment env) {
        return new VCell(v.eval(env));
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {

        IType t = v.typecheck(envT);

        // init new ref
        ICLCompiler.refs_to_compile.append("java -jar jasmin.jar ref_" + t.toString() +".j\n");
        PrintStream refStream = null;
        try{
            refStream = null; refStream = new PrintStream(new File("ref_" + t.toString() + ".j" ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        refStream.println(".class public ref_"+t.toString());
        refStream.println(".super java/lang/Object");
        refStream.println(".field public v " + t.toString());
        refStream.println(".method public <init>()V \n 	aload_0 \n 	invokenonvirtual java/lang/Object/<init>()V \n return");
        refStream.println(".end method");
        refStream.close();

        // init emit
        c.emit("new ref_" + t.toString());
        c.emit("dup");
        c.emit("invokespecial " + "ref_" + t.toString() + "/<init>()V");
        c.emit("dup");
        v.compile(c, envC, envT);
        c.emit("putfield " + "ref_" + t.toString() + "/v " + t.toString());

    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return new TypeRef(v.typecheck(envT));
    }


}
