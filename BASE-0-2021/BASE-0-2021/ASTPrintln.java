class ASTPrintln implements	ASTNode {
    ASTNode arg;
    boolean line;
    public ASTPrintln(ASTNode arg, boolean line){
        this.arg = arg;
        this.line = line;
    }
    @Override
    public IValue eval(Environment<IValue> e ) {
        IValue v1 = arg.eval(e);
        if (line)
            System.out.println(v1.toString());
        else
            System.out.print(v1.toString());
        return v1;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}