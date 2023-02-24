package markup;

public class Text implements TextInterface {
    private final String s;

    public Text(String s) {
        this.s = s;
    }

    public void toMarkdown(StringBuilder ans) {
        ans.append(s);
    }

    public void toHtml(StringBuilder ans) {
        ans.append(s);
    }
}
