package net.bxx2004.pandalib.bukkit.language.application.image;

/**
 * User: bobacadodl
 * Date: 1/25/14
 * Time: 11:03 PM
 */
public enum ImageChar {
    BLOCK('█'),
    DARK_SHADE('▓'),
    MEDIUM_SHADE('▒'),
    LIGHT_SHADE('░');
    private char c;

    ImageChar(char c) {
        this.c = c;
    }

    public char getChar() {
        return c;
    }
}
