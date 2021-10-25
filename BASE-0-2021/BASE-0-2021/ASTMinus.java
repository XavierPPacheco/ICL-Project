public class ASTMinus implements ASTNode {

    ASTNode val;

    public int eval(Environment e) { return -val.eval(e); }

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

