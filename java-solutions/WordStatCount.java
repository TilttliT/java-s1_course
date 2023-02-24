import java.util.*;
import java.io.*;

public class WordStatCount {
    public static void main(String[] args) {
        List<String> words = new ArrayList<String>();
        try {
            MyScanner in = new MyScanner((args[0]), "utf8");
            in.addInPattern(new byte[] {Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER,
                    Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
                    Character.OTHER_LETTER, Character.DASH_PUNCTUATION});
            in.addInPattern('\'');
            try {
                while (in.hasNextWord()) {
                    words.add(in.nextWord().toLowerCase());
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Read file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupproted encoding: " + e.getMessage());
        }

        MyClass[] arr1 = new MyClass[words.size()];
        for (int i = 0; i < words.size(); ++i) {
            arr1[i] = new MyClass(i, 1);
        }
        Comparator<MyClass> comparatorArr1 = new MyComparator1(words);
        Arrays.sort(arr1, comparatorArr1);

        List<MyClass> arr2 = new ArrayList<MyClass>();
        for (int i = 1, count = 1; i <= words.size(); ++i) {
            if (i == words.size() || !words.get(arr1[i].getIndex()).equals(words.get(arr1[i-1].getIndex()))) {
                arr2.add(new MyClass(arr1[i-1].getIndex(), count));
                count = 1;
            } else {
                ++count;
            }
        }

        Comparator<MyClass> comparatorArr2 = new MyComparator2();
        Collections.sort(arr2, comparatorArr2);

        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "utf8"
                    )
            );
            try {
                for (int i = 0; i < arr2.size(); ++i) {
                    out.write(words.get(arr2.get(i).getIndex()) + " " + arr2.get(i).getCount());
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