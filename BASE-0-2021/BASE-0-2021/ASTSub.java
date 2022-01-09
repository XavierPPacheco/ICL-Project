public class ASTSub implements ASTNode {

ASTNode lhs, rhs;

        public IValue eval(Environment e) {
	    IValue v1 = lhs.eval(e);
        if(v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if(v2 instanceof VInt)
                return new VInt(((VInt) v1).getval() - ((VInt) v2).getval());
        }
       throw new InterpretorError("an argument is not an integer.");
	}

    @Override
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        lhs.compile(c, envC, envT);
        rhs.compile(c, envC, envT);
        c.emit("isub");
    }

    @Override
    public IType typecheck(EnvironmentT envT) {
        IType lt = lhs.typecheck(envT);
        if(lt instanceof TInt) {
            IType rt = rhs.typecheck(envT);
            if(rt instanceof TInt)
                return new TInt();
        }
        throw new TypeError("an argument is not an integer.");
    }

    public ASTSub(ASTNode l, ASTNode r)
        {
		lhs = l; rhs = r;
        }
}

