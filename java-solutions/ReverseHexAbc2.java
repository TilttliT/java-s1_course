import java.util.*;

public class ReverseHexAbc2 {
    public static void main(String[] args) {
        MyScanner in1 = new MyScanner(System.in);
        in1.addInPattern(new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'A', 'B', 'C', 'D', 'E', 'F',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'x', 'X', '+', '-'});
        MyDynArray ans = new MyDynArray();
        MyDynArray newLineIndex = new MyDynArray ();
        newLineIndex.add(0);
        while (in1.hasNextWord()) {
            for (int i = 0; i < in1.lineSeparators(); ++i) {
                newLineIndex.add(ans.size());
            }
            String s = in1.nextWord();
            if (s.startsWith("0x") || s.startsWith("0X")) {
                ans.add(Integer.parseUnsignedInt(s.substring(2, s.length()).toUpperCase(), 16));
            } else {
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < s.length(); ++i) {
                    char c = s.charAt(i);
                    if (c >= 'a' && c <= 'j') {
                        temp.append((char)(c - 'a' + '0'));
                    } else {
                        temp.append(c);
                    }
                }
                ans.add(Integer.parseInt(temp.toString()));
            }
        }
        for (int i = 0; i < in1.lineSeparators() - 1; ++i) {
            newLineIndex.add(ans.size());
        }
        in1.close();

        for (int i = ans.size(), j = newLineIndex.size()-1; i >= 0; --i) {
            if (i != ans.size()) {
                String s = Integer.toString(ans.get(i));

                StringBuilder temp = new StringBuilder();
                for (int z = 0; z < s.length(); ++z) {
                    char c = s.charAt(z);
                    if (c >= '0' && c <= '9') {
                        temp.append((char)(c - '0' + 'a'));
                    } else {
                        temp.append(c);
                    }
                }

                System.out.print(temp.toString() + " ");
            }
            while (j >= 0 && newLineIndex.get(j) == i) {
                System.out.println();
                --j;
            }
        }
    }
}