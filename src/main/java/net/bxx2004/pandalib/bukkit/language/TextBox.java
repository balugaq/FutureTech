package net.bxx2004.pandalib.bukkit.language;

public class TextBox {
    private String content;

    public TextBox(String content) {
        this.content = content;
    }

    public void append(String id, String data) {
        content = content + id + "=" + data + ";";
    }

    public String getNode(String id) {
        for (String a : content.split(";")) {
            String b = a.split("=")[0];
            String c = a.split("=")[1];
            if (b.equals(id)) {
                return c;
            }
        }
        return null;
    }

    public String origin() {
        return content;
    }
}
