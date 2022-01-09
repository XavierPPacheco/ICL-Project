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
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
        arg.compile(c, e, envT);
        c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        if(line)
            c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        else
            c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return null;
    }
}