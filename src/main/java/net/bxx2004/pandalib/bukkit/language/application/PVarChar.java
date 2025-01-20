package net.bxx2004.pandalib.bukkit.language.application;

import java.util.HashMap;

/**
 * 类似于变量
 *
 * @since 1.5.5
 */
public class PVarChar {
    private HashMap<String, PVarCharValue> newmap;

    public PVarChar() {
        newmap = new HashMap<String, PVarCharValue>();
    }

    /**
     * 放入一个变量
     *
     * @param key   对应值
     * @param value 变量值
     */
    public void put(String key, PVarCharValue value) {
        newmap.put(key, value);
    }

    /**
     * 取出一个变量
     *
     * @param key 对应值
     * @return 取出的变量
     */
    public Object get(String key) {
        return newmap.get(key).vaule(key);
    }

    /**
     * 取出一个变量
     *
     * @param key 对应值
     * @return 取出的变量
     */
    public String getAsString(String key) {
        return (String) newmap.get(key).vaule(key);
    }

    public Integer getAsInt(String key) {
        return (int) newmap.get(key).vaule(key);
    }

    public Boolean getAsBoolean(String key) {
        return (boolean) newmap.get(key).vaule(key);
    }

    public Byte getAsByte(String key) {
        return (byte) newmap.get(key).vaule(key);
    }

    public Short getAsShort(String key) {
        return (short) newmap.get(key).vaule(key);
    }

    public Long getAsLong(String key) {
        return (long) newmap.get(key).vaule(key);
    }

    public Float getAsFloat(String key) {
        return (float) newmap.get(key).vaule(key);
    }

    public Double getAsDouble(String key) {
        return (double) newmap.get(key).vaule(key);
    }

    public Character getAsChar(String key) {
        return (char) newmap.get(key).vaule(key);
    }

    public String replace(String origin) {
        String result = "";
        for (String name : newmap.keySet()) {
            if (origin.contains(name)) {
                if (result.equalsIgnoreCase("")) {
                    result = origin.replaceAll(name, String.valueOf(newmap.get(name).vaule(name)));
                } else {
                    result = result.replaceAll(name, String.valueOf(newmap.get(name).vaule(name)));
                }
            }
        }
        return result;
    }
}

