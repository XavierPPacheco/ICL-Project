import java.io.PrintStream;

public interface ASTNode {

    int eval();
    void compile(CodeBlock c);

}

class CodeBlock	{
    String	code[];
    int	pc;

    void emit(String	opcode){
        code[pc++]	=	opcode;
    }

    void dump(PrintStream f) {

    }

}

