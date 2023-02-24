package expression;

import java.math.BigDecimal;

public class Divide extends BinMyExpression {
    private static char op = '/';

    public Divide(MyExpression first, MyExpression second) {
        super(first, second);
    }

    @Override
    public char getOp() {
        return op;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int makeOp(int a, int b) {
        return a / b;
    }

    @Override
    public BigDecimal makeOp(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }
}
