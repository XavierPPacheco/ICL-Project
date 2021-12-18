import java.lang.*;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String args[]) {

        Parser0 parser = new Parser0(System.in);
        ASTNode ast;

        while (true) {
            try {
                System.out.print("> ");
                ast = parser.Start();
                Environment<IValue> e = new Environment();
                IValue v = ast.eval(e);

                if(v != null)
                    System.out.println(v);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }


    }
}
