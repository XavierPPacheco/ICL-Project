
import java.util.List;

public class ASTFunction implements ASTNode {

    ASTId id;
    List<String> binds;
    ASTNode body;
    IType returnType;
    IType returnT;

    public ASTFunction(ASTId name, List<String> binds, ASTNode body, IType returnType) {
        this.id = name;
        this.binds = binds;
        this.body = body;
        this.returnType = returnType;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        VFunction function = new VFunction(binds, body, e);
        e.assoc(id.getId(), function);
        return function;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
    }

    @Override
    public IType typecheck(EnvironmentT e) {

        return returnT;
    }

    public IType getType() {
        return returnT;
    }



}
