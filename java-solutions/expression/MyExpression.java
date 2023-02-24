package expression;

import java.math.BigDecimal;

public interface MyExpression extends Expression, BigDecimalExpression, TripleExpression {
    String toString();
    String toMiniString();
    BigDecimal evaluate(BigDecimal x);


    int evaluate(int x);

    int evaluate(int x, int y, int z);
}
