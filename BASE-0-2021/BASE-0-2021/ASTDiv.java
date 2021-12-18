public class ASTDiv implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment e)
    {
        IValue v1 = lhs.eval(e);
        if(v1 instanceof IValue){
            IValue v2 = rhs.eval(e);
            return new VInt(((VInt) v1).getval() / ((VInt) v2).getval());
        }
        throw new InterpretorError("Illegal type in / operation");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("idiv");
    }

    public ASTDiv(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }

}

