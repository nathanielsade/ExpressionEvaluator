import java.util.Map;

/**
 * The class Log represents a logarithm operation between two expressions.
 */
public class Log extends BinaryExpression {
    /**
     * Instantiates a new Log.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public Expression differentiate(String var) {
        Expression rightD = getRight().differentiate(var);
        Expression leftD = getLeft().differentiate(var);
        // if both of the expression contain vars
        if (this.getLeft().getVariables().contains(var) && this.getRight().getVariables().contains(var)) {
            Div tmp = new Div(new Log(new Var("e"), this.getRight()), new Log(new Var("e"), this.getLeft()));
            return tmp.differentiate(var);
        }
        //if both of them are numbers
        if (!this.getLeft().getVariables().contains(var) && !this.getRight().getVariables().contains(var)) {
            return new Num(0);
        }
        if (!this.getLeft().getVariables().contains(var) && this.getRight().getVariables().contains(var)) {
            return new Div(rightD, new Mult(getRight(), new Log(new Var("e"), getLeft())));
        }
        return new Div(new Neg(new Log(this.getLeft(), this.getRight())),
                new Mult(this.getLeft(), new Log(new Var("e"), this.getRight())));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return Math.log(leftValue) / Math.log(rightValue);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.log(this.getRight().evaluate()) / Math.log(this.getLeft().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression newL = this.getLeft().assign(var, expression);
        Expression newR = this.getRight().assign(var, expression);
        return new Log(newL, newR);
    }

    @Override
    public Expression simplify() {
        try {
            //if the expressions are equal
            if (this.getLeft().toString().equals(this.getRight().toString())) {
                return new Num(1);
            }
            //if both of them are numbers return the numerical value
            if (this.getLeft().simplify().simplify() instanceof Num && this.getRight().
                    simplify().simplify() instanceof Num) {
                return new Num(this.evaluate());
            }
            return new Log(this.getLeft().simplify(), this.getRight().simplify());
        } catch (Exception e) {
            // Handle the exception as per your requirements
            // Return a default value or rethrow a different exception if necessary
            return null; // Placeholder value, modify as needed
        }
    }

    @Override
    public String toString() {
        return "log(" + this.getLeft().toString() + ", " + this.getRight().toString() + ")";
    }
}
