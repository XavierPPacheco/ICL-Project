import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ICLCompiler {
    public static  PrintStream frames_to_compile, refs_to_compile;
    public static String BIN_DIR = "./projectBin";
    public static String DEFAULT_FILE = "./Examples/default.icl";
    public static String DEFAULT_DIR = "./Examples/";
    public static void main(String args[]) {

        String fileName = DEFAULT_FILE;
        if(args.length > 0){
            Iterator<String> iterator = Arrays.stream(args).iterator();
            String opt;
            while(iterator.hasNext()){
                opt = iterator.next();
                switch(opt){
                    case "-f":
                        String optFile = iterator.next();
                        if(!optFile.contains("./"))
                            fileName = DEFAULT_DIR.concat(optFile);

                        File file = new File(fileName);
                        if(!file.exists())
                            throw new RuntimeException("File " + fileName + " Does not exist");
                        run(fileName);
                        break;
                    case "-c":
                        File dir = new File(BIN_DIR);
                            for (File f: Objects.requireNonNull(dir.listFiles()))
                                 f.delete();

                        break;
                    case "-h":
                    default:
                        help();
                        break;
                }
            }
        }
        else
            run(fileName);

    }

    private static void run(String fileName){
        try {
            frames_to_compile = new PrintStream(BIN_DIR + "/frames_to_Compile");
            refs_to_compile = new PrintStream(BIN_DIR + "/refs_to_Compile");
            Reader input = new FileReader(fileName);
            Parser0 parser = new Parser0(input);
            CodeBlock code = new CodeBlock();
            EnvironmentC envC = new EnvironmentC(code);
            EnvironmentT envT = new EnvironmentT();

            try {
                ASTNode ast = parser.Start();
                while (ast != null) {
                    ast.typecheck(envT);
                    ast.compile(code, envC, envT);
                    ast = parser.Start();
                }

                PrintStream output = new PrintStream(BIN_DIR + "/Main.j");
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
        frames_to_compile.append("java -jar jasmin.jar " + BIN_DIR+"/Main.j\n");
        frames_to_compile.append("java Main");
        frames_to_compile.close();

    }
    private static void help(){
        System.out.println("\t-h Prints this help menu\n\t-c Cleans the projectBin folder\n\t-f [FILENAME] allows to pass a file input. If only the file name is passed, i.e, test.icl the project will look for the file in the Examples directory\n\t If there exist any chmod errors bellow this line, they should be ignored. We decided not to modify the shell script in order to minimize the complexity, without compromising our project.  ");
    }
}
