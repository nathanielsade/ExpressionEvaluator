import java.util.List;
import java.util.Map;

/**
 * The interface Expression represents a mathematical expression in a tree structure.
 */
public interface Expression {
    /**
     * Returns the expression tree resulting from differentiating the current expression
     * with respect to the variable `var`.
     *
     * @param var the variable to differentiate with respect to
     * @return the differentiated expression
     */
    Expression differentiate(String var);

    /**
     * Evaluates the expression using the variable values provided in the assignment and returns the result.
     * If the expression contains a variable that is not in the assignment, an exception is thrown.
     *
     * @param assignment the variable assignment
     * @return the evaluated result of the expression
     * @throws Exception if evaluation encounters an error, such as missing variables in the assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;
    /**
     * A convenience method to evaluate the expression using an empty assignment.
     *
     * @return the evaluated result of the expression
     * @throws Exception if evaluation encounters an error, such as missing variables in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of variables present in the expression.
     *
     * @return a list of variables in the expression
     */
    List<String> getVariables();
    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression
     */
    // Returns a nice string representation of the expression.
    String toString();
    /**
     * Returns a new expression in which all occurrences of the variable `var` are replaced
     * with the provided expression.
     * The current expression is not modified.
     *
     * @param var        the variable to assign
     * @param expression the new expression to assign
     * @return the assigned expression
     */
    Expression assign(String var, Expression expression);
    /**
     * Simplifies the expression and returns the simplified expression.
     *
     * @return the simplified expression
     */
    Expression simplify();

}