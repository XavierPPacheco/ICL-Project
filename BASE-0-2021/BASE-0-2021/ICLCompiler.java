import java.io.*;

public class ICLCompiler {
    public static  PrintStream frames_to_compile, refs_to_compile;

    public static void main(String args[]) {
        String base_path = "/home/carolina/ICL-Project/BASE-0-2021/BASE-0-2021/";
        String fileName = "./" + args[0];
        try {
            frames_to_compile = new PrintStream(new File("frames_to_Compile"));
            refs_to_compile = new PrintStream(new File("refs_to_Compile"));
            Reader input = new FileReader(fileName);
            Parser0 parser = new Parser0(input);
            CodeBlock code = new CodeBlock();
            EnvironmentC envC = new EnvironmentC(code);
            EnvironmentT envT = new EnvironmentT();

            try {
                ASTNode ast = parser.Start();
                ast.compile(code, envC, envT);
                PrintStream output = new PrintStream(new File("Main.j"));
                code.dump(output);
                System.out.println("Your file was compiled to Main.j");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        } catch (Exception e) {
            System.out.println("File not Found");
        }
        frames_to_compile.append("java -jar jasmin.jar Main.j\n");
        frames_to_compile.append("java Main");
        frames_to_compile.close();

    }
}
