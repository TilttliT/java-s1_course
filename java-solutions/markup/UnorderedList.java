package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkup implements GroupInterface {
    public UnorderedList(List<ListItem> l) {
        super(l, "", "<ul>", "</ul>");
    }
}