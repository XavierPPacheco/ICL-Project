import java.io.*;

public class ICLCompiler {

    public static void main(String args[]) {
        String fileName = "/home/xavier/Desktop/ICL/BASE-0-2021/BASE-0-2021/" + args[0];
        try {
            Reader input = new FileReader(fileName);
            Parser0 parser = new Parser0(input);
            CodeBlock code = new CodeBlock();

            try {
                ASTNode ast = parser.Start();
                ast.compile(code);
                PrintStream output = new PrintStream(new File("/home/xavier/Desktop/ICL/BASE-0-2021/BASE-0-2021/Main.j"));
                code.dump(output);
                System.out.println("Your file was compiled to Main.j");

            } catch (Exception e) {
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }catch (Exception e){
            System.out.println("File not Found");
        }


    }

}
