public class ASTMinus implements ASTNode {

    ASTNode val;

    public int eval() { return -val.eval(); }

    public ASTMinus(ASTNode val)
    {
        this.val = val;
    }

}

