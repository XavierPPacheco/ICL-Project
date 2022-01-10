import java.util.LinkedList;
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
            //c.emit("dup");
            bind.getNode().compile(c, e,envT);
            e.assoc(bind.getVar(),bind.getNode().typecheck(envT));
        }
        envT = addType(envT);
        c.emit("pop");
        body.compile(c, e, envT);
        e.endScope();
    }

    private EnvironmentT addType(EnvironmentT envT) {

        envT = envT.beginScope();

        for(Bind bind : binds) {
            IType t = bind.getNode().typecheck(envT);

            if(bind.getType() != null && !bind.getType().equals(t))
                throw new TypeError("Type mismatch");
            envT.assoc(bind.getVar(), t);
        }

        return envT;
    }
    @Override
    public IType typecheck(EnvironmentT envT) {
        return body.typecheck(addType(envT));
    }

    public ASTDef(List<Bind> binds, ASTNode body) {
        this.binds = binds;
        this.body = body;
    }

}
