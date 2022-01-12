public class ASTWhile implements ASTNode {

    ASTNode guard, body;

    public ASTWhile(ASTNode guard, ASTNode body) {
        this.guard = guard;
        this.body = body;
    }

    @Override
    public IValue eval(Environment e) {
        IValue guardValue = guard.eval(e);
        while (guardValue instanceof VBool && ((VBool) guardValue).getval()) {
            body.eval(e);
            guardValue = guard.eval(e);
        }
        return null;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC,  EnvironmentT envT) {
        String whileTag = c.newTag();
        String endTag = c.newTag();

        c.emit(whileTag + ":");
        guard.compile(c, envC, envT);
        c.emit("ifeq " + endTag);
        body.compile(c, envC, envT);
        //c.emit("pop");
        c.emit("goto " + whileTag);
        c.emit(endTag + ":");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        if (guard.typecheck(envT) instanceof TypeBool)
            return body.typecheck(envT);
        throw new TypeError("While mismatch types");
    }

}
