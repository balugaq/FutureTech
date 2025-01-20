package net.bxx2004.pandalib.bukkit.item.nbt.type;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class NBTArray implements NBTData {
    NBTInteger[] values;
    private String key;
    private String type;

    public NBTArray(NBTInteger... data) {
        values = new NBTInteger[data.length];
        int i = 0;
        for (NBTInteger nbtInteger : data) {
            values[i] = nbtInteger;
            i = i + 1;
        }
        if (values[0].value() instanceof Byte) {
            this.type = "B";
            return;
        }
        if (values[0].value() instanceof Integer) {
            this.type = "I";
            return;
        }
        if (values[0].value() instanceof Long) {
            this.type = "L";
            return;
        }
    }

    public NBTArray(String key, NBTInteger... data) {
        this.key = key;
        values = new NBTInteger[data.length];
        int i = 0;
        for (NBTInteger nbtInteger : data) {
            values[i] = nbtInteger;
            i = i + 1;
        }
        if (values[0].value() instanceof Byte) {
            this.type = "B";
            return;
        }
        if (values[0].value() instanceof Integer) {
            this.type = "I";
            return;
        }
        if (values[0].value() instanceof Long) {
            this.type = "L";
            return;
        }
    }

    public List<String> originData() {
        List<String> list = new ArrayList<>();
        for (NBTData v : values) {
            list.add(v.asString());
        }
        return list;
    }

    //number:[I;1,2,3,4,5]
    //number:[B;1,2,3,4,5]
    //number:[L;12345678,87654321,10000000]
    @Override
    public String asString() {
        if (key == null) {
            String head = "[" + type + ";";
            for (NBTInteger integer : values) {
                head = head + integer.value() + ",";
            }
            String asString = head.substring(0, head.toCharArray().length - 1);
            return asString + "]";
        } else {
            String head = key + ":[" + type + ";";
            for (NBTInteger integer : values) {
                head = head + integer.value() + ",";
            }
            String asString = head.substring(0, head.toCharArray().length - 1);
            return asString + "]";
        }
    }

    public void changeValue(int index, NBTInteger data) {
        values[index] = data;
    }

    @Override
    public NBTType type() {
        return NBTType.ARRAY;
    }

    @Override
    public NBTInteger[] value() {
        return values;
    }

    @Override
    public String key() {
        return key;
    }
}
