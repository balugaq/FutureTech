package net.bxx2004.pandalib.java.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MultipleReturn<T> {
    private HashMap<String, T> kv;
    private T[] v;

    public MultipleReturn(T... t) {
        this.v = t;
    }

    public MultipleReturn(HashMap<String, T> values) {
        kv = values;
    }

    public List<T> values() {
        return v == null ? (List<T>) kv.values() : Arrays.asList(v);
    }

    public T value(int index) {
        return v[index];
    }

    public T value(String key) {
        return kv.get(key);
    }

    @Override
    public String toString() {
        return "MultipleReturn{" +
                "kv=" + kv +
                ", v=" + Arrays.toString(v) +
                '}';
    }
}
