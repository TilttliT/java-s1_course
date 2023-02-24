package expression;

import java.math.BigDecimal;

public class Multiply extends BinMyExpression {
    private static final char OP = '*';

    public Multiply(final MyExpression first, final MyExpression second) {
        super(first, second);
    }

    @Override
    public char getOp() {
        return OP;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int makeOp(final int a, final int b) {
        return a * b;
    }

    @Override
    public BigDecimal makeOp(final BigDecimal a, final BigDecimal b) {
        return a.multiply(b);
    }
}