package markup;

import java.util.List;

public class Paragraph extends AbstractMarkup implements GroupInterface {
    public Paragraph(List<TextInterface> l) {
        super(l, "", "", "");
    }
}