public class Sum {
    public static void main(String[] args) {
        String allStr = " ";
        for (int i = 0; i < args.length; ++i) {
            allStr = allStr + args[i] + " ";
        }

        int result = 0;
        for (int i = 1, lastSpace = 0; i < allStr.length(); ++i) {
            if (Character.isWhitespace(allStr.charAt(i))) {
                if (lastSpace + 1 < i) {
                    String numberStr = allStr.substring(lastSpace + 1, i);
                    int beginIndex = 0;
                    int numberInt = 1;
                    if (numberStr.charAt(0) == '-') {
                        ++beginIndex;
                        numberInt *= -1;
                    } else if (numberStr.charAt(0) == '+') {
                        ++beginIndex;
                    }
                    numberInt *= Integer.parseInt(numberStr.substring(beginIndex));
                    result += numberInt;
                }

                lastSpace = i;
            }
        }

        System.out.println(result);
    }
}