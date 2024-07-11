import java.util.Map;

/**
 * The class Minus represents a subtraction operation between two expressions.
 */
public class Minus extends BinaryExpression {
    /**
     * Instantiates a new Minus.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Expression differentiate(String var) {
        Expression dLeft = getLeft().differentiate(var);
        Expression dRight = getRight().differentiate(var);
        return new Minus(dLeft, dRight);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return leftValue - rightValue;
    }

    @Override
    public double evaluate() throws Exception {
        return this.getLeft().evaluate() - this.getRight().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(this.getLeft().assign(var, expression), this.getRight().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        try {
            //if the expressions are equal
            if (this.getLeft().simplify().toString().equals(this.getRight().toString())) {
                return new Num(0);
            }
            //if the left is zero return the right
            if (this.getLeft().simplify().toString().equals("0.0")) {
                return new Neg(this.getRight()).simplify();
            }
            //if the right is zero return the left
            if (this.getRight().simplify().toString().equals("0.0")) {
                return this.getLeft().simplify();
            }
            //if both of them are numbers evaluate
            if (this.getLeft().simplify() instanceof Num && this.getRight().simplify() instanceof Num) {
                return new Num(this.getLeft().evaluate() + this.getRight().evaluate());
            }
            return new Minus(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }

    @Override
    public String toString() {
        return "(" + this.getLeft().toString() + " - " + this.getRight().toString() + ")";
    }
}
