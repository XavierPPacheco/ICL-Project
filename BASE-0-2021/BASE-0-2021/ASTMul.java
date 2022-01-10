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

        throw new InterpretorError("Illegal operator in  in ");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        lhs.compile(c, e, envT);
        rhs.compile(c, e, envT);
        c.emit("imul");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        IType lt = lhs.typecheck(envT);
        if(lt instanceof TypeInt) {
            IType rt = rhs.typecheck(envT);
            if(rt instanceof TypeInt)
                return new TypeInt();
        }
        throw new TypeError("Div: argument is not an int");
    }

    public ASTMul(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }

}

