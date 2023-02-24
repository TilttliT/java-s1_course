import java.io.*;
import java.util.*;

public class Wspp {
    public static void main (String args[]) {
        Map<String, MyDynArray> m = new LinkedHashMap<String, MyDynArray> ();
        int n = 0;
        try {
            MyScanner in = new MyScanner((args[0]), "utf8");
            in.addInPattern(new byte[] {Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER,
                    Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
                    Character.OTHER_LETTER, Character.DASH_PUNCTUATION});
            in.addInPattern('\'');
            try {
                while (in.hasNextWord()) {
                    ++n;
                    String s = in.nextWord().toLowerCase();
                    if (!m.containsKey(s)) {
                        m.put(s, new MyDynArray());
                    }
                    m.get(s).add(n);
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
                for (Map.Entry<String, MyDynArray> entry : m.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue().size());
                    for (int i = 0; i < entry.getValue().size(); ++i) {
                        out.write(" " + entry.getValue().get(i));
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