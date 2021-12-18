public class ASTDeref implements ASTNode {

    ASTNode v;

    public ASTDeref(ASTNode t) {
        v = t;
    }

    @Override
    public IValue eval(Environment env) {
        IValue val = v.eval(env);
        if (val instanceof VCell)
            return ((VCell) val).get();
        throw new InterpretorError("De ref: argument is not a reference");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC env) {
        /*
        v.compile(c, env, envT);
        TRef t = (TRef) v.typecheck(envT);
        c.emit("checkcast " + t.toString());
        c.emit("getfield " + t.toString() + "/v " + t.getreftype().toString());
         */
    }
    /*
    @Override
    public IType typecheck(EnvironmentT env) {
        IType r = v.typecheck(env);
        if (r instanceof TRef)
            return ((TRef) r).getreftype();
        throw new TypeError("De ref: argument is not a reference");
    }

     */

}

