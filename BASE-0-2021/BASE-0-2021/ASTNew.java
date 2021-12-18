class ASTNew implements	ASTNode {

    ASTNode arg;

    @Override
    public IValue eval(Environment<IValue> e) {
        IValue v1 = arg.eval(e);
        return new VCell(v1);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}