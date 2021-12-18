class ASTAssign	implements	ASTNode {

    ASTNode lhs, rhs;

    public ASTAssign(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }


    public IValue eval(Environment<IValue> env) {
        IValue v1 = lhs.eval(env);
        if (v1 instanceof VCell) {
            IValue v2 = rhs.eval(env);
            ((VCell) v1).set(v2);
            return v2;
        }
        throw new InterpretorError("illegal	arguments	to	:=	operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}