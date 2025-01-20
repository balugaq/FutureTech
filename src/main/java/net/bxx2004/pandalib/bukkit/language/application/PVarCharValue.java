package net.bxx2004.pandalib.bukkit.language.application;

/**
 * VarCharVaule - 用于储存变量
 */
public abstract class PVarCharValue {
    public static PVarCharValue getInstance(Object value) {
        return new PVarCharValue() {
            @Override
            public Object vaule(String key) {
                return value;
            }
        };
    }

    abstract public Object vaule(String key);
}
