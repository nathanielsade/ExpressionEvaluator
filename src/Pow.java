import java.util.Map;

/**
 * The class Pow represents the power function.
 */
public class Pow extends BinaryExpression {
    /**
     * Instantiates a new Pow.
     *
     * @param left  the left expression (base)
     * @param right the right expression (exponent)
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public Expression differentiate(String var) {
        Expression leftDiff = this.getLeft().differentiate(var);
        Expression rightDiff = this.getRight().differentiate(var);
        return new Mult(
                new Pow(this.getLeft(), this.getRight()),
                new Plus(new Mult(leftDiff, new Div(this.getRight(), this.getLeft())),
                        new Mult(rightDiff, new Log(new Var("e"), this.getLeft()))));
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return Math.pow(leftValue, rightValue);
    }
    @Override
    public double evaluate() throws Exception {
        double leftValue = this.getLeft().evaluate();
        double rightValue = this.getRight().evaluate();
        //squer root of negative
        if (leftValue < 0 && rightValue % 1 != 0) {
            throw new Exception("Invalid power: negative base with fractional exponent");
        }
        return Math.pow(leftValue, rightValue);
    }
    @Override
    public String toString() {
        return "(" + this.getLeft().toString() + "^" + this.getRight().toString() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(this.getLeft().assign(var, expression), this.getRight().assign(var, expression));
    }
    @Override
    public Expression simplify() {
        try {
            Expression lValue = this.getLeft().simplify();
            Expression rValue = this.getRight().simplify();
            // if both of the expression are numbers
            if (lValue instanceof Num && rValue instanceof Num) {
                if (lValue.evaluate() < 0 && rValue.evaluate() % 1 != 0) {
                    throw new ArithmeticException("Invalid power");
                }
                return new Num(Math.pow(this.getLeft().evaluate(), this.getRight().evaluate()));
            }
            return new Pow(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            throw new ArithmeticException("Error");
        }
    }
}
