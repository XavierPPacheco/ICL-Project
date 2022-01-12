import java.util.List;

public class ASTDef implements ASTNode{

    List<Bind> binds;
    ASTNode	body;

    @Override
    public IValue eval(Environment<IValue> e) {
        e = e.beginScope();

        for(Bind b: binds)
            e.assoc(b.getVar(), b.getNode().eval(e));

        IValue val = body.eval(e);
        e.endScope();

        return val;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
        e = e.beginScope();

        for(Bind bind : binds) {
            c.emit("aload_3");
            bind.getNode().compile(c, e,envT);
            e.assoc(bind.getVar(),bind.getNode().typecheck(envT));
        }
        envT = checkBindsTypes(envT);
        c.emit("pop");
        body.compile(c, e, envT);
        envT.endScope();
        e.endScope();
    }

    private EnvironmentT checkBindsTypes(EnvironmentT envT) {

        envT = envT.beginScope();

        for(Bind bind : binds) {
            IType t = bind.getNode().typecheck(envT);

            if(bind.getType() != null && !bind.getType().equals(t))
                throw new TypeError("Type mismatch between " +  bind.getType() + " and " + t );
            envT.assoc(bind.getVar(), t);
        }
        return envT;
    }
    @Override
    public IType typecheck(EnvironmentT envT) {
        envT = checkBindsTypes(envT);
        return body.typecheck(envT);
    }

    public ASTDef(List<Bind> binds, ASTNode body) {
        this.binds = binds;
        this.body = body;
    }

}
