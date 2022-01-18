
import java.util.List;

public class VFunction implements IValue {

    List<String> parameters;
    ASTNode body;
    Environment<IValue> environment;

    public VFunction(List<String> parameters, ASTNode body){
        this.parameters = parameters;
        this.body = body;
    }

    public VFunction(List<String> parameters, ASTNode body, Environment<IValue> environment){
        this(parameters, body);
        this.environment = environment;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public ASTNode getBody() {
        return body;
    }

    public Environment<IValue> getEnvironment() {
        return environment;
    }
}
