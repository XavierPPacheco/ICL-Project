import java.util.LinkedList;
import java.util.List;

public class ASTDef implements ASTNode{

    List<Bind> binds;
    ASTNode	body;

    @Override
    public int eval(Environment e) {

        List<Integer> vals = new LinkedList<>();
        for(Bind b: binds)
            vals.add(b.getNode().eval(e));

        e = e.beginScope();

        int index = 0;
        for(Bind b: binds) {
            e.assoc(b.getVar(), vals.get(index));
            index++;
        }
        int val = body.eval(e);
        e.endScope();

        return val;
    }

    @Override
    public void compile(CodeBlock c) {

    }

    public ASTDef(List<Bind> binds, ASTNode body) {
        this.binds = binds;
        this.body = body;
    }

}
