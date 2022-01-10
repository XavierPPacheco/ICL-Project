public class ASTIf implements ASTNode{

    ASTNode guard, then_node, else_node;

    public ASTIf(ASTNode t, ASTNode t1, ASTNode t2) {
        guard = t;
        then_node = t1;
        else_node = t2;
    }

    @Override
    public IValue eval(Environment env) {
        IValue gval = guard.eval(env);
        if (gval instanceof VBool) {
            if (((VBool) gval).getval())
                return then_node.eval(env);
            else if (else_node != null)
                return else_node.eval(env);
            else
                return null;
        }
        throw new InterpretorError("argument is not a boolean in if");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        guard.compile(c, envC, envT);
        String guardTag = c.newTag();
        String endTag = c.newTag();
        c.emit("ifeq " + guardTag);
        then_node.compile(c, envC, envT);
        c.emit("goto " + endTag);
        c.emit(guardTag + ":");
        else_node.compile(c, envC, envT);
        c.emit(endTag + ":");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        IType t1 = guard.typecheck(envT);
        if (t1 instanceof TypeBool) {
            IType tt = then_node.typecheck(envT);
            IType te = else_node.typecheck(envT);
            if (tt.equals(te))
                return te;
            throw new TypeError("mismatch in then or else");
        }
        throw new TypeError("condition is not boolean type in if");
    }



}
