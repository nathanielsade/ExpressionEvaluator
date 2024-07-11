import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class Num represents a numerical constant.
 */
public class Num implements Expression {
    private final double num;

    /**
     * Instantiates a new Num.
     *
     * @param num the numerical value
     */
    public Num(double num) {
        this.num = num;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return num;
    }

    @Override
    public double evaluate() throws Exception {
        return num;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return Double.toString(num);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
