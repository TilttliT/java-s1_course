package markup;

import java.util.List;

public class ListItem extends AbstractMarkup implements Markup {
    public ListItem(List<GroupInterface> l) {
        super(l, "", "<li>", "</li>");
    }
}
