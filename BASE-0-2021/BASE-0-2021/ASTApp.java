
import java.util.Iterator;
import java.util.List;

public class ASTApp implements ASTNode{

    ASTId functionId;
    List<ASTNode> arguments;
    IType type;

    public ASTApp(ASTId functionId, List<ASTNode> arguments) {
        this.functionId = functionId;
        this.arguments = arguments;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        IValue fun = functionId.eval(e);
        VFunction function = (VFunction) fun;

        Environment<IValue> envF = function.getEnvironment().beginScope();
        List<String> parameters = function.getParameters();

        if(parameters.size() != arguments.size())
            throw new InterpretorError( "error in the number of arguments in the function. Expected " + arguments.size() + " found " + parameters.size());

        Iterator<String> paramsIterator = parameters.iterator();
        Iterator<ASTNode> argsIterator = arguments.iterator();

        while(paramsIterator.hasNext())
            envF.assoc(paramsIterator.next(), new VRef(argsIterator.next().eval(e)));

        return function.getBody().eval(envF);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {

    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return null;
    }

}
