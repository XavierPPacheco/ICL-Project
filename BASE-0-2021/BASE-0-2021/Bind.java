public class Bind {

    private String var;
    private ASTNode node;

    public Bind(String var, ASTNode node) {
        this.node = node;
        this.var = var;
    }

    public ASTNode getNode() {
        return node;
    }

    public String getVar() {
        return var;
    }

}