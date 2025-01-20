package net.bxx2004.pandalib.bukkit.item.nbt.type;

@Deprecated
public class NBTString implements NBTData {
    private String key;
    private String toString;

    public NBTString(String key, String s) {
        this.key = key;
        this.toString = s;
    }

    public NBTString(String s) {
        this.toString = s;
    }

    @Override
    public String asString() {
        if (key == null) {
            return "\"" + toString + "\"";
        } else {
            return key + ":" + "\"" + toString + "\"";
        }
    }

    public void changeValue(String t) {
        this.toString = t;
    }

    @Override
    public NBTType type() {
        return NBTType.STRING;
    }

    @Override
    public String value() {
        return toString;
    }

    @Override
    public String key() {
        return key;
    }
}
