package expression;

import java.math.BigDecimal;

public class Variable implements MyExpression {
    final private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if ("x".equals(name)) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            Variable x = (Variable) other;
            return name.equals(x.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
