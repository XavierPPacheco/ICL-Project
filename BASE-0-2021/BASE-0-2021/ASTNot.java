import javax.xml.validation.TypeInfoProvider;

public class ASTNot implements ASTNode {

    ASTNode v;

    public ASTNot(ASTNode t) {
        v = t;
    }

    @Override
    public IValue eval(Environment env) {
        IValue lv = v.eval(env);
        if (lv instanceof VBool)
            return new VBool(!((VBool) lv).getval());

        throw new InterpretorError("NOT: argument is not a boolean");
    }


    @Override
    public void compile(CodeBlock c, EnvironmentC e) {

    }
}
