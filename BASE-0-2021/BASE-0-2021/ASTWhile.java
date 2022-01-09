public class ASTWhile implements ASTNode {

    ASTNode guard, body;

    public ASTWhile(ASTNode t, ASTNode t1) {
        guard = t;
        body = t1;
    }

    @Override
    public IValue eval(Environment e) {
        IValue guardval = guard.eval(e);
        while (guardval instanceof VBool && ((VBool) guardval).getval()) {
            body.eval(e);
            guardval = guard.eval(e);
        }
        return null;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        String whileTag = c.newTag();
        String endTag = c.newTag();

        c.emit(whileTag + ":");
        guard.compile(c, envC, envT);
        c.emit("ifeq " + endTag);
        body.compile(c, envC, envT);
        c.emit("goto " + whileTag);
        c.emit(endTag + ":");
    }

    @Override
    public IType typecheck(EnvironmentT env) {
        if (guard.typecheck(env) instanceof TBool)
            return null;
        throw new TypeError("guard not a boolean");
    }

}
