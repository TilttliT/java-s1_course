import java.util.*;
import java.io.*;

public class WordStatInput {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            "utf8"
                    )
            );
            Map<String, Integer> dict = new LinkedHashMap<String, Integer> ();
            try {
                String line = in.readLine();
                while (line != null) {
                    int n = line.length();
                    for (int i = 0, j = -1; i <= n; ++i) {
                        if (i == n || !Character.isLetter(line.charAt(i)) && line.charAt(i) != '\'' &&
                                Character.getType(line.charAt(i)) != Character.DASH_PUNCTUATION) {

                            if (i - j > 1) {
                                String s = line.substring(j+1, i).toLowerCase();
                                int x = 0;
                                if (dict.containsKey(s)) {
                                    x = dict.get(s);
                                }
                                dict.put(s, x + 1);
                            }
                            j = i;
                        }
                    }

                    line = in.readLine();
                }
            } finally {
                in.close();
            }

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "utf8"
                    )
            );
            try {
                for (String key: dict.keySet()) {
                    out.write(key + " " + dict.get(key));
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read or write file: " + e.getMessage());
        }
    }
}