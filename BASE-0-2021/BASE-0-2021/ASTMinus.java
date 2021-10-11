public class ASTMinus implements ASTNode {

    ASTNode val;

    public int eval() { return -val.eval(); }

    @Override
    public void compile(CodeBlock c) {
        c.emit("sipush -" + c);

    }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

