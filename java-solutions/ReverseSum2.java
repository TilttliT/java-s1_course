import java.util.Scanner;

public class ReverseSum2 {
    public static void main(String[] args) {
        Scanner in1 = new Scanner(System.in);

        MyDynArray arr = new MyDynArray();
        MyDynArray ind = new MyDynArray();
        while (in1.hasNextLine()) {
            Scanner in2 = new Scanner(in1.nextLine());
            while (in2.hasNextInt()) {
                arr.add(in2.nextInt());
            }
            ind.add(arr.size());
        }

        MyDynArray col = new MyDynArray();
        for (int i = 0, j = 0, pref = 0, last = 0; i <= arr.size(); ++i) {
            while (j < ind.size() && ind.get(j) <= i) {
                System.out.println();
                pref = 0;
                ++j;
                last = i;
            }
            if (i < arr.size()) {
                if (i - last >= col.size()) {
                    col.add(0);
                }
                col.set(i - last, col.get(i - last) + arr.get(i));
                pref += col.get(i - last);
                System.out.print(pref + " ");
            }
        }
    }
}