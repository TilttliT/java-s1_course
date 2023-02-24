import java.math.BigInteger;

public class SumBigIntegerHex {
    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;
        for (String s: args) {
            for (int i = 0, lastSpace = -1; i <= s.length(); ++i) {
                if (i == s.length() || Character.isWhitespace(s.charAt(i))) {
                    if (lastSpace + 1 < i) {
                        String numberStr = s.substring(lastSpace + 1, i);

                        int radix = 10;
                        int index = 0;
                        if (numberStr.length() >= 2 &&
                                numberStr.substring(0, 2).toLowerCase().equals("0x")) {

                            radix = 16;
                            index = 2;
                        }

                        result = result.add(new BigInteger(numberStr.substring(index), radix));
                    }

                    lastSpace = i;
                }
            }
        }

        System.out.println(result);
    }
}
