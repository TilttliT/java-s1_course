import java.util.Comparator;
import java.util.List;

class MyComparator1 implements Comparator<MyClass> {
    private final List<String> words;

    public MyComparator1 (List<String> words) {
        this.words = words;
    }

    @Override
    public int compare(MyClass a, MyClass b) {
        if (!words.get(a.getIndex()).equals(words.get(b.getIndex()))) {
            return words.get(a.getIndex()).compareTo(words.get(b.getIndex()));
        } else {
            return b.getIndex() - a.getIndex();
        }
    }
}