import java.util.List;
import java.util.Map;
/**
 * The type Cos.
 */
public class Cos extends UnaryExpression {
    /**
     * Instantiates a new Cos.
     *
     * @param expression the expression
     */
    Cos(Expression expression) {
        super(expression);
    }

    @Override
    public Expression differentiate(String var) {
        Expression innerDiff = this.getExpression().differentiate(var);
        return new Neg(new  Mult(new Sin(this.getExpression()), innerDiff));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value = this.getExpression().evaluate(assignment);
        return Math.cos(Math.toRadians(value));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(this.getExpression().evaluate()));
    }

    @Override
    public List<String> getVariables() {
        return this.getExpression().getVariables();
    }
    @Override
    public String toString() {
        return "cos(" + getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        try {
            if (this.getExpression().simplify() instanceof Num) {
                return new Num(this.getExpression().evaluate());
            }
            return new Cos(this.getExpression().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }
}
