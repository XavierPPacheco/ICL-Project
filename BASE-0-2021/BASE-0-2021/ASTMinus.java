public class ASTMinus implements ASTNode {

    ASTNode val;

    public int eval() { return -val.eval(); }

    @Override
    public void compile(CodeBlock c) {
        val.compile(c);
        c.emit("ineg");

    }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

