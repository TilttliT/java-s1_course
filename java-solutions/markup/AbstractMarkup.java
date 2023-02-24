package markup;

import java.util.List;

public abstract class AbstractMarkup {
    private final StringBuilder sMarkdown;
    private final StringBuilder sHtml;
    private final String sep;
    private final String sep1;
    private final String sep2;

    protected AbstractMarkup(List<? extends Markup> l, String sep, String sep1, String sep2) {
        sMarkdown = new StringBuilder();
        sHtml = new StringBuilder();
        this.sep = sep;
        this.sep1 = sep1;
        this.sep2 = sep2;
        for (Markup x : l) {
            x.toMarkdown(sMarkdown);
            x.toHtml(sHtml);
        }
    }

    public void toMarkdown(StringBuilder ans) {
        ans.append(sep);
        ans.append(sMarkdown);
        ans.append(sep);
    }

    public void toHtml(StringBuilder ans) {
        ans.append(sep1);
        ans.append(sHtml);
        ans.append(sep2);
    }
}