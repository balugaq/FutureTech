package net.bxx2004.pandalib.bukkit.item.nbt.type;

@Deprecated
public class NBTInteger<T> implements NBTData, NBTNumber {
    private String key;
    private String type;
    private String toString;
    private T v;

    public NBTInteger(T v) {
        if (v instanceof Byte) {
            this.v = v;
            this.type = "b";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Short) {
            this.v = v;
            this.type = "s";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Integer) {
            this.v = v;
            this.type = "";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Long) {
            this.v = v;
            this.type = "L";
            this.toString = String.valueOf(v);
            return;
        }
    }

    public NBTInteger(String key, T v) {
        this.key = key;
        if (v instanceof Byte) {
            this.v = v;
            this.type = "b";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Short) {
            this.v = v;
            this.type = "s";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Integer) {
            this.v = v;
            this.type = "";
            this.toString = String.valueOf(v);
            return;
        }
        if (v instanceof Long) {
            this.v = v;
            this.type = "L";
            this.toString = String.valueOf(v);
            return;
        }
    }

    public void changeValue(T t) {
        this.v = t;
    }

    @Override
    public String asString() {
        if (key == null) {
            return toString + type;
        } else {
            return key + ":" + toString + type;
        }
    }

    @Override
    public NBTType type() {
        return NBTType.INTEGER;
    }

    @Override
    public T value() {
        return v;
    }

    @Override
    public String key() {
        return key;
    }
}
