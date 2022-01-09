
public class ASTBool implements ASTNode {

    VBool b;

    ASTBool(boolean b0){
        b = new VBool(b0);
    }

    @Override
    public IValue eval(Environment env) {
        return b;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC env, EnvironmentT envT) {
        if(b.getval())
            c.emit("sipush 1");
        else
            c.emit("sipush 0");
    }


    @Override
    public IType typecheck(EnvironmentT env) {
        return new TBool();
    }




}
