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
        throw new InterpretorError("if: argument is not a boolean");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC env) {
        // TODO Auto-generated method stub
        guard.compile(c, env);
        String l1 = c.newLabel();
        String l2 = c.newLabel();
        c.emit("ifeq " + l1);
        then_node.compile(c, env);
        c.emit("goto " + l2);
        c.emit(l1 + ":");
        else_node.compile(c, env);
        c.emit(l2 + ":");
    }


}
