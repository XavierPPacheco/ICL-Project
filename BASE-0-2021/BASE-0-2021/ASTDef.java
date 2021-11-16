import java.util.LinkedList;
import java.util.List;

public class ASTDef implements ASTNode{

    List<Bind> binds;
    ASTNode	body;

    @Override
    public int eval(Environment e) {
        e = e.beginScope();

        for(Bind b: binds)
            e.assoc(b.getVar(), b.getNode().eval(e));

        int val = body.eval(e);
        e.endScope();

        return val;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        e = e.beginScope();

        for(Bind bind : binds) {
            c.emit("aload_3");
            bind.getNode().compile(c, e);
            e.assoc(bind.getVar());
        }
        //c.emit("pop");
        body.compile(c, e);
        e.endScope();
    }

    public ASTDef(List<Bind> binds, ASTNode body) {
        this.binds = binds;
        this.body = body;
    }

}
