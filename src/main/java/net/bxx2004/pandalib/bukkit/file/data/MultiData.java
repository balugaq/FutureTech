package net.bxx2004.pandalib.bukkit.file.data;

import java.util.function.Function;

public abstract class MultiData<T> {
    public static final String YAML = "YAML";
    public static final String MYSQL = "MYSQL";
    public static final String SQLITE = "SQLITE";
    public static final String JSON = "JSON";
    public static final String TXT = "TXT";
    public static final Function OTHER = (Function<String, String>) s -> s;

    abstract public T get(String type);

    abstract public T getWithArgs(String type, String... args);

    abstract public String name();

    abstract public void set(T t);

    public final T getException(String type) {
        if (get(type) == null) {
            throw new NullPointerException();
        } else {
            return get(type);
        }
    }

    public final T getOrDefault(String type, T t) {
        if (get(type) == null) {
            return t;
        } else {
            return get(type);
        }
    }

    public final T getException(String type, String... args) {
        if (getWithArgs(type, args) == null) {
            throw new NullPointerException();
        } else {
            return getWithArgs(type, args);
        }
    }

    public final T getOrDefault(String type, T t, String... args) {
        if (getWithArgs(type, args) == null) {
            return t;
        } else {
            return getWithArgs(type, args);
        }
    }
}
