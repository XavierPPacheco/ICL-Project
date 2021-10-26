import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public interface ASTNode {

    int eval(Environment e);
    void compile(CodeBlock c);

}

class CodeBlock	{
    String	code[];
    int	pc;

    public CodeBlock() {
        this.pc = 0;
        code = new String[250];
    }

    void emit(String	opcode){
        code[pc++]	=	opcode;
    }

    void dump(PrintStream f)  {
       // File output = new File("/home/xavier/Desktop/ICL/BASE-0-2021/BASE-0-2021/final.j");
        File sample = new File("/home/xavier/Desktop/ICL/BASE-0-2021/BASE-0-2021/Template.j");
        try{
            Scanner myReader = new Scanner(sample);
            boolean flag = false;
           // PrintStream stream = new PrintStream(output);
            String line = "";
            while(myReader.hasNextLine() && !flag) {
                line = myReader.nextLine();
                if (line.contains("; START"))
                    flag = true;
                f.println(line);
            }

            for(int i = 0; i< pc;i++){
                f.print("\n" + code[i]);
            }

            f.print("\n");

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

