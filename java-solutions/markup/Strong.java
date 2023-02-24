package markup;

import java.util.List;

public class Strong extends AbstractMarkup implements TextInterface  {
    public Strong(List<TextInterface> l) {
        super(l, "__", "<strong>", "</strong>");
    }
}
