import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public interface ASTNode {

    IValue eval(Environment<IValue> e);
    void compile(CodeBlock c, EnvironmentC e);

}

class CodeBlock	{
    String	code[];
    int	pc;

    public CodeBlock() {
        this.pc = 0;
        code = new String[250];
    }

    void emit(String opcode){
        code[pc++] = opcode;
    }

    void dump(PrintStream f)  {
        File sample = new File("./Template.j");
        try{
            Scanner myReader = new Scanner(sample);
            boolean flag = false;
            String line = "";

            while(myReader.hasNextLine() && !flag) {
                line = myReader.nextLine();
                if (line.contains("; START"))
                    flag = true;
                f.println(line);
            }
            f.print("\n\taconst_null");
            f.print("\n\tastore_3\n");

            for(int i = 0; i< pc;i++){
                f.print("\t" + code[i] + "\n" );
            }

            while(myReader.hasNextLine())
                f.println(myReader.nextLine());

            myReader.close();
            f.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File doesn't exist");
        }
    }

}

