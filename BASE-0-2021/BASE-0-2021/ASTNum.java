public class ASTNum implements ASTNode {

int val;

        public int eval(Environment e) { return val; }

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
            //c.emit("aload_3");
            c.emit("sipush " + val);
    }

    public ASTNum(int n)
        {
	   val = n;
        }

}

