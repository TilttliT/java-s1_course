package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup implements TextInterface {
    public Emphasis(List<TextInterface> l) {
        super(l, "*", "<em>", "</em>");
    }
}