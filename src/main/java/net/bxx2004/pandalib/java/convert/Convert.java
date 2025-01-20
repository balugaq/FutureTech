package net.bxx2004.pandalib.java.convert;

import java.util.ArrayList;
import java.util.List;

public interface Convert<T> {
    public T parseObject(String s);

    public String parseString(T t);

    public boolean equal(T t, T t1);

    public boolean equal(String s, String s1);

    public class Manager {

        protected Manager(ConvertOption option, Class<? extends Convert> clazz) {

        }

        public static <T> Convert<T> make(Class type, ConvertOption option) {
            try {
                return (Convert<T>) type.getDeclaredConstructor(ConvertOption.class).newInstance(option);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static <T> Convert<T> make(Class type) {
            try {
                return (Convert<T>) type.getDeclaredConstructor(ConvertOption.class).newInstance(new ConvertOption());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String translateJSON(String s) {
            String[] list;
            List<String> r = new ArrayList<>();
            if (s.startsWith("{")) {
                list = s.substring(1, s.length() - 2).split(",");
            } else {
                list = s.split(",");
            }
            for (String item : list) {
                String[] a = item.split(":");
                for (int i = 0; i < a.length; i += 2) {
                    if (!a[i].contains("\"")) {
                        a[i] = "\"" + a[i] + "\"";
                    }
                }
                for (int i = 1; i < a.length; i += 2) {
                    if (a[i].contains("[") || a[i].contains("{")) {

                    } else {
                        try {
                            int aa = Integer.parseInt(a[i].trim());
                        } catch (Exception e) {
                            a[i] = "\"" + a[i] + "\"";
                        }
                    }
                }
                String item_result = a[0] + ":" + a[1];
                r.add(item_result);
            }
            String re = "";
            for (String s1 : r) {
                re += s1 + ",";
            }
            return "{" + re.substring(0, re.length() - 1) + "}";
        }
    }
}
