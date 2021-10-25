public class ASTId implements ASTNode{

    String id;

    @Override
    public int eval(Environment e) {
        return	e.find(id);
    }

    @Override
    public void compile(CodeBlock c) {
        // TODO
    }

    public ASTId(String id) {
        this.id = id;
    }

}
