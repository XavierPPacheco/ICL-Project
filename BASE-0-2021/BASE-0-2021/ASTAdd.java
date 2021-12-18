public class ASTAdd implements ASTNode {

ASTNode lhs, rhs;

    public IValue eval(Environment e) {
        IValue v1 = lhs.eval(e);

        if(v1 instanceof VInt){
            IValue v2 = rhs.eval(e);
            if(v2 instanceof VInt)
                return new VInt(((VInt)v1).getval()+ ((VInt) v2).getval());
        }
       throw new InterpretorError("Illegal types to + operator");
	}

    @Override
    public void compile(CodeBlock c, EnvironmentC e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("iadd");
    }

    public ASTAdd(ASTNode l, ASTNode r) {
		lhs = l; rhs = r;
        }

}

