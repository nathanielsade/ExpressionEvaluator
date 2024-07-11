import java.util.List;
import java.util.Map;

/**
 * The class Neg represents a negation of an expression.
 */
public class Neg extends UnaryExpression {
    /**
     * Instantiates a new Neg.
     *
     * @param expression the expression to be negated
     */
    Neg(Expression expression) {
        super(expression);
    }
    @Override
    public Expression differentiate(String var) {
        Expression expression1 = this.getExpression().differentiate(var);
        return new Neg(expression1);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -this.getExpression().evaluate(assignment);
    }
    @Override
    public double evaluate() throws Exception {
        return -this.getExpression().evaluate();
    }
    @Override
    public List<String> getVariables() {
        return this.getExpression().getVariables();
    }
    @Override
    public String toString() {
        return "(" + "-" + getExpression().toString() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.getExpression().assign(var, expression));
    }
    @Override
    public Expression simplify() {
        try {
            if (this.getExpression().simplify() instanceof Num) {
                return new Num(-this.getExpression().simplify().evaluate());
            }
            return new Neg(this.getExpression().simplify());
        } catch (Exception e) {
            throw new ArithmeticException("Error");
        }
    }
}
