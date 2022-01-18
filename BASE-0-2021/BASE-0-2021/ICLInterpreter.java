import java.lang.*;
import java.util.Scanner;

public class ICLInterpreter {

    public static void main(String args[]) {

        Parser0 parser = new Parser0(System.in);
        ASTNode ast;
        Environment<IValue> e = new Environment();

        while (true) {
            try {
                System.out.print("> ");
                ast = parser.Start();
                IValue v = ast.eval(e);

                //if(v != null)
                //    System.out.println(v);
            } catch (Exception exp) {
                System.out.println(exp);
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }


    }
}
