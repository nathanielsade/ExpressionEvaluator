
/**
 * @author Nethaniel Sade
 * @version 19.0.2
 * @since 2023-05-18
 * The type Main.
 */
 import java.util.Map;
 import java.util.TreeMap;

/**
 * The type Main.
 */
public class ExpressionsTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Expression ex = new Plus(new Mult(new Num(2), new Var("x")),
                new Plus(new Sin(new Mult(new Num(4), new Var("y"))), new Pow(new Var("e"), new Var("x"))));
        Var y = new Var("y");
        Var x = new Var("x");
        ex = new Mult(new Log(new Mult(new Num(9), x),new Mult(new Num(9), x)), new Mult(new Num(2),y));
        System.out.println(ex);
        System.out.println(ex.simplify());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        double value = ex.evaluate(assignment);
        System.out.println(value);
        System.out.println(ex.differentiate("x"));
        System.out.println(ex.differentiate("x").evaluate(assignment));
        System.out.println(ex.differentiate("x").simplify());
    }
}