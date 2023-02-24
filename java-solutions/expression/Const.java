package expression;

import java.math.BigDecimal;

public class Const implements MyExpression {
    private final Number value;

    public Const(BigDecimal value) {
        this.value = value;
    }

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal) value;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Const) {
            Const x = (Const) other;
            return value.equals(x.value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
