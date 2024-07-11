import java.util.Map;
/**
 * The class Plus represents the addition operation.
 */
public class Plus extends BinaryExpression {
    /**
     * Instantiates a new Plus.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public Plus(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public Expression differentiate(String var) {
        Expression lExpression = this.getLeft().differentiate(var);
        Expression rExpression = this.getRight().differentiate(var);
        return new Plus(lExpression, rExpression);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return leftValue + rightValue;
    }
    @Override
    public double evaluate() throws Exception {
        return this.getLeft().evaluate() + this.getRight().evaluate();
    }
    @Override
    public String toString() {
        return "(" + getLeft().toString() + " + " + getRight().toString() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.getLeft().assign(var, expression), this.getRight().assign(var, expression));
    }
    @Override
    public Expression simplify() {
        try {
            //if the second expression is zero return only the left
            if (this.getRight().simplify().toString().equals("0.0")) {
                return this.getLeft().simplify();
            }
            //if the first expression is zero return only the right
            if (this.getLeft().simplify().toString().equals("0.0")) {
                return this.getRight().simplify();
            }
            //if both of them are numbers evaluate
            if (this.getRight().simplify() instanceof Num && this.getLeft().simplify() instanceof Num) {
                return new Num(this.getLeft().simplify().evaluate() + this.getRight().simplify().evaluate());
            }
            return new Plus(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }
}
