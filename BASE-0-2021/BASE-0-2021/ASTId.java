public class ASTId implements ASTNode{

    String id;

    @Override
    public IValue eval(Environment<IValue> e) {
        return e.find(id);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        c.emit("aload_3");
        e.find(id);
    }

    public ASTId(String id) {
        this.id = id;
    }

}
