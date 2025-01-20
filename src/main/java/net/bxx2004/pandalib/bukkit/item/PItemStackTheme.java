package net.bxx2004.pandalib.bukkit.item;

public class PItemStackTheme {
    private String[] lore;
    private char b;

    public PItemStackTheme(char b, String... theme) {
        this.b = b;
        this.lore = theme;
    }

    public String[] getLore() {
        return lore;
    }

    public void set(String content) {
        String a = "";
        for (String b : lore) {
            a = a + b + ",";
        }
        a = a.substring(0, a.length());
        a = a.replaceFirst(String.valueOf(b), content);
        int i = 0;
        for (String c : a.split(",")) {
            lore[i] = c;
            i = i + 1;
        }
    }
}
