import java.util.Map;

/**
 * The class Mult represents a multiplication operation between two expressions.
 */
public class Mult extends BinaryExpression {
    /**
     * Instantiates a new Mull.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Expression differentiate(String var) {
        Expression leftDiff = getLeft().differentiate(var);
        Expression rightDiff = getRight().differentiate(var);
        Expression leftMul = new Mult(leftDiff, getRight());
        Expression rightMul = new Mult(getLeft(), rightDiff);
        return new Plus(leftMul, rightMul);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return leftValue * rightValue;
    }

    @Override
    public double evaluate() throws Exception {
        return this.getLeft().evaluate() * this.getRight().evaluate();
    }

    @Override
    public String toString() {
        return "(" + this.getLeft().toString() + " * " + this.getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(this.getLeft().assign(var, expression), this.getRight().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        try {
            if (this.getLeft().simplify().toString().equals("0.0") || this.getRight().simplify().
                    toString().equals("0.0")) {
                return new Num(0);
            }
            if (this.getLeft().simplify().toString().equals("1.0")) {
                return this.getRight().simplify();
            } else if (this.getRight().simplify().toString().equals("1.0")) {
                return this.getLeft().simplify();
            }
            if (this.getRight().simplify() instanceof Num && this.getLeft().simplify() instanceof Num) {
                return new Num(this.getLeft().evaluate() * this.getRight().evaluate());
            }
            return new Mult(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }
}
