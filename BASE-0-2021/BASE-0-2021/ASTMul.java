public class ASTMul implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment e)
    {
        IValue v1 = lhs.eval(e);
        if(v1 instanceof  VInt){
            IValue v2 = rhs.eval(e);
            if(v2 instanceof VInt)
                return new VInt(((VInt) v1).getval() * ((VInt) v2).getval());
        }

        throw new InterpretorError("Illegal operator on * operation");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("imul");
    }

    public ASTMul(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }

}

