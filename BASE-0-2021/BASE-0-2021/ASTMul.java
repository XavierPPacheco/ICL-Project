public class ASTMul implements ASTNode {

    ASTNode lhs, rhs;

    public int eval()
    {
        int v1 = lhs.eval();
        int v2 = rhs.eval();
        return v1*v2;
    }

    @Override
    public void compile(CodeBlock c) {
        lhs.compile(c);
        rhs.compile(c);
        c.emit("imul");
    }

    public ASTMul(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }

}

