package net.bxx2004.pandalib.bukkit.item.nbt.type;

@Deprecated
public class NBTDecimal<T> implements NBTData, NBTNumber {
    private String key;
    private String type;
    private String toString;
    private T v;

    public NBTDecimal(T v) {
        if (v instanceof Float) {
            this.v = v;
            this.type = "f";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Double) {
            this.v = v;
            this.type = "d";
            this.toString = String.valueOf(v);
            return;
        }
    }

    public NBTDecimal(String key, T v) {
        this.key = key;
        if (v instanceof Float) {
            this.v = v;
            this.type = "f";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Double) {
            this.v = v;
            this.type = "d";
            this.toString = String.valueOf(v);
            return;
        }
    }

    @Override
    public String asString() {
        if (key == null) {
            return toString + type;
        } else {
            return type + ":" + toString + type;
        }
    }

    @Override
    public NBTType type() {
        return NBTType.DECIMAL;
    }

    @Override
    public T value() {
        return v;
    }

    @Override
    public String key() {
        return key;
    }

    public void changeValue(T t) {
        this.v = t;
    }
}
