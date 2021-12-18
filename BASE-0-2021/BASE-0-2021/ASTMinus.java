public class ASTMinus implements ASTNode {

    ASTNode val;

    public IValue eval(Environment e) {
        IValue v1 = val.eval(e);
        if(v1 instanceof VInt)
            return new VInt(-((VInt) v1).getval());
        throw new InterpretorError("Illegal operation on - operand");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        val.compile(c, e);
        c.emit("ineg");

    }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

