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
            case "~=":
                return new VBool(((VInt) lvalue).getval() != ((VInt)rvalue).getval());
        }
        throw new InterpretorError("operation not valid in comparator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        lhs.compile(c, e, envT);
        rhs.compile(c, e, envT);
        String l1 = c.newTag();
        String l2 = c.newTag();
        c.emit("isub");
        switch (operation) {
            case "==":
                c.emit("ifeq " + l1);
                break;
            case ">":
                c.emit("isub");
                break;
            case "<":
                c.emit("iflt " + l1);
                break;
            case ">=":
                c.emit("ifge " + l1);
                break;
            case "<=":
                c.emit("ifle " + l1);
                break;
            case "~=":
                c.emit("if_icmpne" + l1);
                break;
        }
        c.emit("sipush 0");
        c.emit("goto " + l2);
        c.emit(l1 + ":\n sipush 1");
        c.emit(l2 + ":");
    }
    @Override
    public IType typecheck(EnvironmentT envT) {
        IType lt = lhs.typecheck(envT);
        if(lt instanceof TInt) {
            IType rt = rhs.typecheck(envT);
            if(rt instanceof TInt)
                return new TBool();
        }
        throw new TypeError("an argument is not a int");
    }

}
