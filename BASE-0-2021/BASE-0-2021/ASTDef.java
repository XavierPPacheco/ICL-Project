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
            bind.getNode().compile(c, e,envT);
            e.assoc(bind.getVar());
        }
        //c.emit("pop");
        body.compile(c, e, envT);
        e.endScope();
    }

    private EnvironmentT addTypes(EnvironmentT env) {
        List<IType> vals = new LinkedList<>();
        for(Bind b: binds) {
            IType t = b.getNode().typecheck(env);
            vals.add(t);
            if(!b.getType().equals(t))
                throw new TypeError("Type mismatch");
        }
        env = env.beginScope();
        int index = 0;
        for(Bind b: binds) {
            env.assoc(b.getVar(), vals.get(index));
            index++;
        }
        return env;
    }
    @Override
    public IType typecheck(EnvironmentT envT) {
        return body.typecheck(addTypes(envT));
    }

    public ASTDef(List<Bind> binds, ASTNode body) {
        this.binds = binds;
        this.body = body;
    }

}
