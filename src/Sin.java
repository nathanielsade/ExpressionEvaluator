import java.util.List;
import java.util.Map;
/**
 * the class sin represent the function of the sin.
 */
public class Sin extends UnaryExpression {

    /**
     * Instantiates a new Sin.
     *
     * @param expression the expression inside the sin function
     */
    Sin(Expression expression) {
        super(expression);
    }
    @Override
    public Expression differentiate(String var) {
        //sin'(f(x)) = f'(x)cos(f(x))
        Expression insideDiff = this.getExpression().differentiate(var);
        Expression cosDiff = new Cos(this.getExpression());
        return new Mult(cosDiff, insideDiff);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value = this.getExpression().evaluate(assignment);
        return Math.sin(Math.toRadians(value));
    }
    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(this.getExpression().evaluate()));
    }
    @Override
    public List<String> getVariables() {
        return this.getExpression().getVariables();
    }
    @Override
    public String toString() {
        return "sin(" + getExpression().toString() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.getExpression().assign(var, expression));
    }
    @Override
    public Expression simplify() {
        try {
            if (this.getExpression().simplify() instanceof Num) {
                return new Num(this.getExpression().evaluate());
            }
            return this.getExpression().simplify();
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }
}
