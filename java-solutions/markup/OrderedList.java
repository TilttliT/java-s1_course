package markup;

import java.util.List;

public class OrderedList extends AbstractMarkup implements GroupInterface {
    public OrderedList(List<ListItem> items) {
        super(items, "", "<ol>", "</ol>");
    }
}