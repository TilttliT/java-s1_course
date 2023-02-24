package expression;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BinMyExpression implements MyExpression {
    private final MyExpression first;
    private final MyExpression second;

    public BinMyExpression(final MyExpression first, final MyExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract char getOp();
    protected abstract int getPriority();

    public String toString() {
        return "(" + first + " " + getOp() + " " + second + ")";
    }

    private boolean check1(final BinMyExpression exp) {
        return getPriority() < exp.getPriority();
    }

    private boolean check2(final BinMyExpression exp) {
        return (getOp() == '/' || (exp.getOp() == '/' || getOp() == '-') &&
                getPriority() == exp.getPriority());
    }

    public String toMiniString() {
        final StringBuilder sb = new StringBuilder();

        if (first instanceof BinMyExpression otherBin && check1(otherBin)) {
            sb.append("(");
            sb.append(first.toMiniString());
            sb.append(")");
        } else {
            sb.append(first.toMiniString());
        }

        sb.append(" ").append(getOp()).append(" ");

        if (second instanceof BinMyExpression otherBin &&
                (check1(otherBin) || check2(otherBin))) {
            sb.append("(");
            sb.append(second.toMiniString());
            sb.append(")");
        } else {
            sb.append(second.toMiniString());
        }

        return sb.toString();
    }

    protected abstract int makeOp(int a, int b);

    protected abstract BigDecimal makeOp(BigDecimal a, BigDecimal b);

    @Override
    public int evaluate(final int x) {
        return makeOp(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return makeOp(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(final BigDecimal x) {
        return makeOp(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof BinMyExpression otherBin) {
            return other.getClass() == getClass() &&
                    first.equals(otherBin.first) &&
                    second.equals(otherBin.second);
        }

        return false;
    }

    public int hashCode() {
        return Objects.hash(first, second, getOp());
    }
}
