import java.io.*;
import java.util.*;

public class WsppSortedSecondG {
    public static void main (String args[]) {
        Map<String, MyDynArray> d = new TreeMap<String, MyDynArray> ();
        MyDynArray line = new MyDynArray();
        int n = 0;
        int m = 0;
        try {
            MyScanner in = new MyScanner((args[0]), "utf8");
            in.addInPattern(new byte[] {Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER,
                    Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
                    Character.OTHER_LETTER, Character.DASH_PUNCTUATION});
            in.addInPattern('\'');
            try {
                while (in.hasNextWord()) {
                    if (in.lineSeparators() > 0) {
                        ++m;
                    }
                    String s = in.nextWord().toLowerCase();
                    if (!d.containsKey(s)) {
                        d.put(s, new MyDynArray());
                    }
                    d.get(s).add(n);
                    line.add(m);
                    ++n;
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Read file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupproted encoding: " + e.getMessage());
        }

        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "utf8"
                    )
            );
            try {
                for (Map.Entry<String, MyDynArray> entry : d.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue().size());
                    for (int i = 1, cnt = 1; i < entry.getValue().size(); ++i) {
                        int cur = entry.getValue().get(i);
                        int prev = entry.getValue().get(i-1);
                        if (line.get(cur) == line.get(prev)) {
                            ++cnt;
                        } else {
                            cnt = 1;
                        }
                        if (cnt % 2 == 0) {
                            out.write(" " + (entry.getValue().get(i) + 1));
                        }
                    }
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open output file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot write file: " + e.getMessage());
        }
    }
}