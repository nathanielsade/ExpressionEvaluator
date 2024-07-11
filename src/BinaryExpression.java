import java.util.ArrayList;
import java.util.List;

/**
 * The type Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    /**
     * The Right.
     */
    private final Expression right;
    /**
     * The Left.
     */
    private final Expression left;

    /**
     * Instantiates a new Binary expression.
     *
     * @param a the a
     * @param b the b
     */
    BinaryExpression(Expression a, Expression b) {
        this.left = a;
        this.right = b;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public Expression getRight() {
        return this.right;
    }

    /**
     * Get left expression.
     *
     * @return the expression
     */
    public  Expression getLeft() {
        return this.left;
    }
    @Override
    public List<String> getVariables() {
        List<String>  list = new ArrayList<>();
        if (this.left.getVariables() != null) {
            list.addAll(this.left.getVariables());
        }
        if (this.right.getVariables() != null) {
            list.addAll(this.right.getVariables());
        }
        return list;
    }
}
