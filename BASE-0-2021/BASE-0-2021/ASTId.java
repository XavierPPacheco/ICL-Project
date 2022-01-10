public class ASTId implements ASTNode{

    String id;

    @Override
    public IValue eval(Environment<IValue> e) {
        return e.find(id);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        c.emit("aload_3");
        e.find(id, envT.find(id));
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return envT.find(id) ;
    }

    public ASTId(String id) {
        this.id = id;
    }

}
