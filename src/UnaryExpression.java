/**
 * The class is an abstract base class for unary expressions in an arithmetic expression.
 * It extends the class.
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * The Expression.
     */
    private final Expression expression;

    /**
     * Constructs a new instance with the specified expression.
     *
     * @param expression the expression within the unary expression
     */
    UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Gets expression.
     *
     * @return the expression
     */
    public Expression getExpression() {
        return this.expression;
    }
}
