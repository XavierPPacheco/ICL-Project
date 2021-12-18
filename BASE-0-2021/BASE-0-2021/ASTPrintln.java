class	ASTPrintln	implements	ASTNode {



    ASTNode arg;
    public ASTPrintln(ASTNode arg){
        this.arg = arg;
    }
    @Override
    public IValue eval(Environment<IValue> e) {
        IValue v1 = arg.eval(e);
        System.out.println(v1.toString());
        return v1;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}