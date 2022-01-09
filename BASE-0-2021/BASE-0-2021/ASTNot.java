public class ASTNot implements ASTNode {

    ASTNode boolValue;

    public ASTNot(ASTNode t) {
        boolValue = t;
    }

    @Override
    public IValue eval(Environment env) {
        IValue lv = boolValue.eval(env);
        if (lv instanceof VBool)
            return new VBool(!((VBool) lv).getval());

        throw new InterpretorError("argument is not a boolean in not operator");
    }


    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        c.emit("sipush 1");
        boolValue.compile(c, envC, envT);
        c.emit("isub");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        if(boolValue instanceof TBool)
            return new TBool();
        throw new TypeError("argument is not a boolean in not operator");
    }
}
