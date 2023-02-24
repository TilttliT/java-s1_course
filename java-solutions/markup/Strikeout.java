package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup implements TextInterface {
    public Strikeout(List<TextInterface> l) {
        super(l, "~", "<s>", "</s>");
    }
}