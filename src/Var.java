import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * The class represents a variable expression in an arithmetic expression.
 * It implements the interface.
 */
public class Var implements Expression {
    private final String var;
    /**
     * Constructs a new instance with the specified variable name.
     *
     * @param var the variable name
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    public Expression differentiate(String var) {
        // The derivative of a variable with respect to itself is 1,
        // otherwise it is 0.
        if (this.var.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // Check if the assignment contains the value for the variable.
        // If found, return the value; otherwise, throw an exception.
        if (assignment.containsKey(this.var)) {
            return assignment.get(this.var);
        } else {
            throw new Exception("Variable " + this.var + " not found");
        }
    }

    @Override
    public double evaluate() throws Exception {
        // Evaluating a variable expression without an assignment is not supported.
        throw new Exception("evaluate " + this.var + "is NaN");
    }

    @Override
    public List<String> getVariables() {
        // Return a list containing the variable name.
        List<String> vars = new ArrayList<>();
        vars.add(this.var);
        return vars;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        // If the assigned variable matches this variable, return the new expression;
        // otherwise, return this variable expression unchanged.
        if (this.var.equals(var)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression simplify() {
        // Variable expressions are already simplified, so return the expression unchanged.
        return this;
    }

    @Override
    public String toString() {
        // Return the variable name as a string representation.
        return var;
    }
}
