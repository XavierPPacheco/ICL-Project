import java.util.List;

public class ASTDef implements ASTNode{

    List<Pair<String,ASTNode>> init;
    ASTNode	body;

    @Override
    public int eval(Environment e) {
        return 0;
    }

    @Override
    public void compile(CodeBlock c) {

    }

    public ASTDef() {
    }

}
