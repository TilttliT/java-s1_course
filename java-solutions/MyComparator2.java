import java.util.Comparator;

class MyComparator2 implements Comparator<MyClass> {
    @Override
    public int compare(MyClass a, MyClass b) {
        if (a.getCount() != b.getCount()) {
            return a.getCount() - b.getCount();
        } else {
            return a.getIndex() - b.getIndex();
        }
    }
}