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
    public void compile(CodeBlock c, EnvironmentC envC, EnvironmentT envT) {
        lhs.compile(c, envC, envT);
        rhs.compile(c, envC, envT);
        c.emit("iadd");
    }

    @Override
    public IType typecheck(EnvironmentT env) {
        IType lt = lhs.typecheck(env);
        if(lt instanceof TypeInt) {
            IType rt = rhs.typecheck(env);
            if(rt instanceof TypeInt)
                return new TypeInt();
        }
        throw new TypeError("argument is not an int in add operation");
    }

    public ASTAdd(ASTNode l, ASTNode r) {
		lhs = l; rhs = r;
        }

}

