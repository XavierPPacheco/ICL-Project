public class ASTCompareBool implements  ASTNode{


    ASTNode lhs,rhs;
    String operation;

    public ASTCompareBool(ASTNode lhs, ASTNode rhs, String operation){
        this.lhs=lhs;
        this.rhs=rhs;
        this.operation = operation;

    }
    @Override
    public IValue eval(Environment<IValue> e) {
        IValue lvalue = lhs.eval(e);
        IValue rvalue = rhs.eval(e);

        if(!(lvalue instanceof VBool) || !(rvalue instanceof VBool))
            throw new InterpretorError("argument is not a boolean in comparator");

        switch (operation){
            case "&&":
                return new VBool(((VBool)lvalue).getval() && ((VBool)rvalue).getval());
            case "||" :
                return new VBool(((VBool)lvalue).getval() || ((VBool)rvalue).getval());
        }
        throw new InterpretorError("operation not valid");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        lhs.compile(c, envC, envT);
        rhs.compile(c, envC, envT);
        switch (operation){
            case "&&":
                c.emit("iand");
            case "||" :
                c.emit("ior");
        }
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return new TypeBool();
    }
}
