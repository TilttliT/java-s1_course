import java.util.Arrays;

class MyDynArray {
    private int size;
    private int[] arr;
    
    public MyDynArray () {
        size = 0;
        arr = new int[size];
    }
    
    public int get (int i) {
        return arr[i];
    }
    
    public void set (int i, int x) {
        arr[i] = x;
    }
    
    public void add (int x) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size*3/2 + 1);
        }
        arr[size] = x;
        ++size;
    }
    
    public int size () {
        return size;
    }
}
