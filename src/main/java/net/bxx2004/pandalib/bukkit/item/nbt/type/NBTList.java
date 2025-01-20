package net.bxx2004.pandalib.bukkit.item.nbt.type;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class NBTList implements NBTData {
    List<NBTData> values = new ArrayList<NBTData>();
    private String key;

    public NBTList(String key, NBTData... data) {
        this.key = key;
        for (NBTData base : data) {
            values.add(base);
        }
    }

    public List<String> originData() {
        List<String> list = new ArrayList<>();
        for (NBTData v : values) {
            list.add(v.asString());
        }
        return list;
    }

    //names:["Mike","Jane","Bob"]
    @Override
    public String asString() {
        String head = key + ":[";
        for (NBTData v : values) {
            head = head + v.asString() + ",";
        }
        String asString = head.substring(0, head.toCharArray().length - 1);
        return asString + "]";
    }

    public void changeValue(int index, NBTData data) {
        values.set(index, data);
    }

    @Override
    public NBTType type() {
        return NBTType.LIST;
    }

    @Override
    public List<NBTData> value() {
        return values;
    }

    @Override
    public String key() {
        return key;
    }
}
