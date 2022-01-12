public class ASTDeref implements ASTNode {

    ASTNode ref;

    public ASTDeref(ASTNode t) {
        ref = t;
    }

    @Override
    public IValue eval(Environment env) {
        IValue val = ref.eval(env);
        if (val instanceof VCell)
            return ((VCell) val).get();
        throw new InterpretorError("argument is not a reference in deref operation");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        ref.compile(c, envC, envT);
        TypeRef t = (TypeRef) ref.typecheck(envT);
        c.emit("checkcast " + t.toString());
        c.emit("getfield " + t.toString() + "/v " + t.getreftype().toString());
    }

    @Override
    public IType typecheck(EnvironmentT env) {
        IType r = ref.typecheck(env);
        if (r instanceof TypeRef)
            return ((TypeRef) r).getreftype();
        throw new TypeError("argument is not a reference in deref operation");
    }

}

