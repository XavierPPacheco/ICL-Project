public class ASTMinus implements ASTNode {

    ASTNode val;

    public IValue eval(Environment e) {
        IValue v1 = val.eval(e);
        if(v1 instanceof VInt)
            return new VInt(-((VInt) v1).getval());
        throw new InterpretorError("Illegal operation in subtraction operation");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        val.compile(c, e, envT);
        c.emit("ineg");

    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        IType lt = val.typecheck(envT);
        if(lt instanceof TInt) {
                return new TInt();
        }
        throw new TypeError("argument is not an int in subtraction operation");
    }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

