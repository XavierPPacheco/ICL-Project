import java.lang.*;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String args[]) {

        Parser0 parser = new Parser0(System.in);

        while (true) {
            try {
                System.out.print("> ");
                ASTNode ast = parser.Start();
                Environment e = new Environment();
                System.out.println(ast.eval(e));
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }


    }
}
