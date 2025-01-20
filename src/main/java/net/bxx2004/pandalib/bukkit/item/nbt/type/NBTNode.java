package net.bxx2004.pandalib.bukkit.item.nbt.type;

import net.bxx2004.pandalib.bukkit.item.nbt.NBTMeta;
import net.bxx2004.pandalib.bukkit.util.PNMS;
import net.bxx2004.pandalib.java.reflect.PJVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.bxx2004.pandalib.bukkit.item.nbt.NBTMeta.numberType;

@Deprecated
public class NBTNode implements NBTData {
    private String key;
    private List<NBTData> maps;

    public NBTNode(String key, List<NBTData> data) {
        this.key = key;
        this.maps = data;
    }

    public NBTNode(String key, Map map) {
        this(map);
        this.key = key;
    }

    public NBTNode(List<NBTData> data) {
        this.maps = data;
    }

    public NBTNode(Map map) {
        List<NBTData> list = new ArrayList<>();
        for (Object key_cache : map.keySet()) {
            String key = key_cache.toString();
            Object value = map.get(key);
            if (NBTMeta.type(value) == NBTType.INTEGER) {
                if (numberType(value.toString()).equals("byte")) {
                    list.add(new NBTInteger<Byte>(key, Byte.parseByte(value.toString().replaceAll("b", ""))));
                }
                if (numberType(value.toString()).equals("short")) {
                    list.add(new NBTInteger<Short>(key, Short.parseShort(value.toString().replaceAll("s", ""))));
                }
                if (numberType(value.toString()).equals("int")) {
                    list.add(new NBTInteger<Integer>(key, Integer.parseInt(value.toString())));
                }
                if (numberType(value.toString()).equals("long")) {
                    list.add(new NBTInteger<Long>(key, Long.parseLong(value.toString().replaceAll("L", ""))));
                }
            }
            if (NBTMeta.type(value) == NBTType.DECIMAL) {
                if (numberType(value.toString()).equals("float")) {
                    list.add(new NBTDecimal<Float>(key, Float.parseFloat(value.toString().replaceAll("f", ""))));
                }
                if (numberType(value.toString()).equals("double")) {
                    list.add(new NBTDecimal<Double>(key, Double.parseDouble(value.toString().replaceAll("d", ""))));
                }
            }
            if (NBTMeta.type(value) == NBTType.STRING) {
                list.add(new NBTString(key, value.toString().replaceAll("\"", "")));
            }
            //names:["Mike","Jane","Bob"]
            if (NBTMeta.type(value) == NBTType.LIST) {
                Object nbtlist = value;
                List nbtbase;
                if (PNMS.is1_17UPServer()) {
                    nbtbase = (List) new PJVariable(nbtlist).getValue("c");
                } else {
                    nbtbase = (List) new PJVariable(nbtlist).getValue("list");
                }
                NBTData[] data = new NBTData[nbtbase.size()];
                int i = 0;
                for (Object base : nbtbase) {
                    if (NBTMeta.type(base) == NBTType.NODE) {
                        Map map1;
                        if (!PNMS.is1_17UPServer()) {
                            map1 = (Map) new PJVariable(base).getValue("map");
                        } else {
                            map1 = (Map) new PJVariable(base).getValue("x");
                        }
                        data[i] = new NBTNode(map1);
                    }
                    if (NBTMeta.type(base) == NBTType.STRING) {
                        data[i] = new NBTString(base.toString());
                    }
                    i = i + 1;
                }
                list.add(new NBTList(key, data));
            }
            if (NBTMeta.type(value) == NBTType.NODE) {
                Map map1;
                if (!PNMS.is1_17UPServer()) {
                    map1 = (Map) new PJVariable(value).getValue("map");
                } else {
                    map1 = (Map) new PJVariable(value).getValue("x");
                }
                list.add(new NBTNode(key, map1));
            }
            //number:[I;1,2,3,4,5]
            if (NBTMeta.type(value) == NBTType.ARRAY) {
                String type = value.toString().substring(1, 2);
                String[] v = value.toString().split(";")[1].replaceAll("]", "").split(",");
                if (type.equals("B")) {
                    NBTInteger<Byte>[] b = new NBTInteger[v.length];
                    int i = 0;
                    for (String a : v) {
                        b[i] = new NBTInteger<>(Byte.parseByte(a));
                        i = i + 1;
                    }
                    list.add(new NBTArray(key, b));
                }
                if (type.equals("I")) {
                    NBTInteger<Integer>[] b = new NBTInteger[v.length];
                    int i = 0;
                    for (String a : v) {
                        b[i] = new NBTInteger<>(Integer.parseInt(a));
                        i = i + 1;
                    }
                    list.add(new NBTArray(key, b));
                }
                if (type.equals("L")) {
                    NBTInteger<Long>[] b = new NBTInteger[v.length];
                    int i = 0;
                    for (String a : v) {
                        b[i] = new NBTInteger<>(Long.parseLong(a));
                        i = i + 1;
                    }
                    list.add(new NBTArray(key, b));
                }
            }
        }
        this.maps = list;
    }

    public void changeValue(String key, NBTData data) {
        int i = 0;
        for (NBTData data1 : maps) {
            if (data1.key().equals(key)) {
                if (data.key() == null) {
                    maps.set(i, new NBTData() {
                        @Override
                        public String asString() {
                            return key + ":" + data.asString();
                        }

                        @Override
                        public NBTType type() {
                            return data.type();
                        }

                        @Override
                        public Object value() {
                            return data.value();
                        }

                        @Override
                        public String key() {
                            return key;
                        }
                    });
                } else {
                    maps.set(i, data);
                }
            }
            i = i + 1;
        }
    }

    public NBTData getData(String tag) {
        for (NBTData data : maps) {
            if (data.key().equals(tag)) {
                return data;
            }
        }
        return null;
    }

    //pack:{ver:1,num:2}
    @Override
    public String asString() {
        String head;
        if (key == null) {
            head = "{";
        } else {
            head = key + ":{";
        }
        for (NBTData data : maps) {
            head = head + data.asString() + ",";
        }
        String asString = head.substring(0, head.toCharArray().length - 1);
        return asString + "}";
    }

    @Override
    public NBTType type() {
        return NBTType.NODE;
    }

    @Override
    public List<NBTData> value() {
        return maps;
    }

    @Override
    public String key() {
        return key;
    }
}
