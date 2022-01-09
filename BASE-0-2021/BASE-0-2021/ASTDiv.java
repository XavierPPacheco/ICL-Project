public class ASTDiv implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment e)
    {
        IValue v1 = lhs.eval(e);
        if(v1 instanceof IValue){
            IValue v2 = rhs.eval(e);
            return new VInt(((VInt) v1).getval() / ((VInt) v2).getval());
        }
        throw new InterpretorError("Illegal division operation");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        lhs.compile(c, e, envT);
        rhs.compile(c, e, envT);
        c.emit("idiv");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        IType lt = lhs.typecheck(envT);
        if(lt instanceof TInt) {
            IType rt = rhs.typecheck(envT);
            if(rt instanceof TInt)
                return new TInt();
        }
        throw new TypeError("argument is not an int in division operation");
    }

    public ASTDiv(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }



}

