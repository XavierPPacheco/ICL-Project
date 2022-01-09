public class Bind {

    private String var;
    private ASTNode node;
    private IType type;


    public Bind(String var, ASTNode node, IType type) {
        this.node = node;
        this.var = var;
        this.type = type;
    }

    public ASTNode getNode() {
        return node;
    }

    public String getVar() {
        return var;
    }

    public IType getType() {
        return type;
    }


}