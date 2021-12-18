public class ASTCompare implements ASTNode{

    ASTNode lhs, rhs;
    String operation;

    public ASTCompare( ASTNode lhs, ASTNode rhs,String operation){
        this.lhs = lhs;
        this.rhs = rhs;
        this.operation =operation;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        IValue lvalue = lhs.eval(e);
        IValue rvalue = rhs.eval(e);

        if(!(lvalue instanceof VInt) || !(rvalue instanceof VInt))
            throw new InterpretorError("argument is not an integer");

        switch(operation){
            case "==":
                return new VBool(((VInt)lvalue).getval() == ((VInt)rvalue).getval());
            case ">":
                return new VBool(((VInt)lvalue).getval() > ((VInt)rvalue).getval());
            case "<":
                return new VBool(((VInt)lvalue).getval() < ((VInt)rvalue).getval());
            case ">=":
                return new VBool(((VInt)lvalue).getval() >= ((VInt)rvalue).getval());
            case "<=":
                return new VBool(((VInt)lvalue).getval() <= ((VInt)rvalue).getval());
        }
        throw new InterpretorError("operation not valid");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}
