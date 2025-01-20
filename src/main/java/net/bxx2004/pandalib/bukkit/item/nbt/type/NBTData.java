package net.bxx2004.pandalib.bukkit.item.nbt.type;

@Deprecated
public interface NBTData {
    public String asString();

    public NBTType type();

    public Object value();

    public String key();
}
