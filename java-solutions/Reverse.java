public class Reverse {
    public static void main(String[] args) {
        MyScanner in1 = new MyScanner(System.in);
        int[] ans = new int[1000000];
        int[] newLineIndex = new int[1000000];
        int n = 0;
        int m = 0;
        while (in1.hasNextLine()) {
            newLineIndex[m] = n;
            MyScanner in2 = new MyScanner(in1.nextLine());
            while (in2.hasNextInt()) {
                ans[n] = in2.nextInt();
                ++n;
            }
            ++m;
        }

        for (int i = n, j = m-1; i >= 0; --i) {
            if (i != n) {
                System.out.print(ans[i] + " ");
            }
            while (j >= 0 && newLineIndex[j] == i) {
                System.out.println();
                --j;
            }
        }
    }
}