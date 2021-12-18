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

    /*
    private void createRefClass(String class_name, IType t) {
        PrintStream classStream = null;
        try {
            classStream = new PrintStream(new File(class_name + ".j"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        classStream.println(".class public ref_"+t.toString());
        classStream.println(".super java/lang/Object");
        classStream.println(".field public v " + t.toString());
        classStream.println(".method public <init>()V \n 	aload_0 \n 	invokenonvirtual java/lang/Object/<init>()V \n return");
        classStream.println(".end method");
        classStream.close();
    }
    */

    @Override
    public void compile(CodeBlock c, EnvironmentC env) {
        /*
        IType t = v.typecheck(envT);

        String class_name = "ref_" + t.toString();
        createRefClass(class_name, t);
        c.emit("new " + class_name);
        c.emit("dup");
        c.emit("invokespecial " + class_name + "/<init>()V");
        c.emit("dup");
        v.compile(c, env, envT);
        c.emit("putfield " + class_name + "/v " + t.toString());
        */
    }

    /*
    @Override
    public IType typecheck(EnvironmentT env) {
        return new TRef(v.typecheck(env));
    }
     */

}

/*
 * .class ref_class .super java/lang/Object .field public v r; .end method
 */