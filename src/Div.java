import java.util.Map;
/**
 * The type Div.
 */
public class Div extends BinaryExpression {

    /**
     * Instantiates a new Div.
     *
     * @param left  the left
     * @param right the right
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Expression differentiate(String var) {
        Expression leftDiff = this.getLeft().differentiate(var);
        Expression rightDiff = this.getRight().differentiate(var);
        return new Div(
                new Minus(new Mult(leftDiff, this.getRight()), new Mult(this.getLeft(), rightDiff)),
                new Pow(this.getRight(), new Num(2)));
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return leftValue / rightValue;
    }
    @Override
    public double evaluate() throws Exception {
        if (this.getRight().evaluate() == 0) {
            throw new Exception("Invalid divider");
        }
        return this.getLeft().evaluate() / this.getRight().evaluate();
    }
    @Override
    public String toString() {
        return "(" + this.getLeft().toString() + " / " + this.getRight().toString() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(this.getLeft().assign(var, expression), this.getRight().assign(var, expression));
    }
    @Override
    public Expression simplify() {
        try {
            //if the expressions are equals
            if (this.getLeft().simplify().toString().equals(this.getRight().toString())) {
                return new Num(1);
            }
            //if the right expression is the number 1
            if (this.getRight().simplify().toString().equals("1.0")) {
                return this.getLeft().simplify();
            }
            if (this.getLeft().simplify() instanceof Num && this.getRight().simplify() instanceof Num) {
                return new Num(this.getLeft().evaluate() / this.getRight().evaluate());
            }
            return new Div(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }
}
