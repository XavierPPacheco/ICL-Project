public class ASTNum implements ASTNode {

int val;

        public IValue eval(Environment e) { return new VInt(val); }

    @Override
    public void compile(CodeBlock c, EnvironmentC e, EnvironmentT envT) {
            c.emit("sipush " + val);
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        return new TypeInt();
    }

    public ASTNum(int n)
        {
	   val = n;
        }

}

