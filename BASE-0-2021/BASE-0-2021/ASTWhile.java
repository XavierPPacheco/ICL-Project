public class ASTWhile implements ASTNode {

    ASTNode guard, body;

    public ASTWhile(ASTNode t, ASTNode t1) {
        guard = t;
        body = t1;
    }

    @Override
    public IValue eval(Environment e) {
        IValue gval = guard.eval(e);
        while (gval instanceof VBool && ((VBool) gval).getval()) {
            body.eval(e);
            gval = guard.eval(e);
        }
        return null;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC env) {
        // TODO Auto-generated method stub
        String l1 = c.newLabel();
        String l2 = c.newLabel();
        c.emit(l1 + ":");
        guard.compile(c, env);
        c.emit("ifeq " + l2);
        body.compile(c, env);
        c.emit("goto " + l1);
        c.emit(l2 + ":");
    }

}
