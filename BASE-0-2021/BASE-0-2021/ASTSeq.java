public class ASTSeq implements ASTNode {
    ASTNode lhs, rhs;

    public ASTSeq(ASTNode t1, ASTNode t2) {
        lhs = t1 ; rhs = t2;
    }

    @Override
    public IValue eval(Environment env) {
        lhs.eval(env);
        return rhs.eval(env);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        lhs.compile(c, envC, envT);
        rhs.compile(c, envC, envT);
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return rhs.typecheck(envT);
    }

}
