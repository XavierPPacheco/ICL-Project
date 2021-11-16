public class ASTMinus implements ASTNode {

    ASTNode val;

    public int eval(Environment e) { return -val.eval(e); }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        val.compile(c, e);
        c.emit("ineg");

    }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

