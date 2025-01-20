package net.bxx2004.pandalib.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射一个类的成员变量
 *
 * @since 1.5.2
 */
public class PJVariable {
    private Class clazz;
    private Object object;

    /**
     * 根据类构造
     *
     * @param clazz 类
     */
    public PJVariable(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 根据对象构造
     *
     * @param object 对象
     */
    public PJVariable(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    /**
     * 获取该类所有成员变量
     *
     * @return 所有成员变量
     */
    public List<Field> getFields() {
        List<Field> list = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            list.add(field);
        }
        return list;
    }

    /**
     * 根据字段名获取字段
     *
     * @param name 字段名
     * @return 字段
     */
    public Field getField(String name) {
        try {
            Field field = this.clazz.getDeclaredField(name);
            try {
                field.setAccessible(true);
            } catch (Exception e) {
            }
            return field;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字段名获取值
     *
     * @param name 字段名
     * @return 值
     */
    public Object getValue(String name) {
        try {
            return this.getField(name).get(this.object);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return this.clazz.getField(name).get(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 修改一个字段的值
     *
     * @param name  字段名
     * @param value 值
     * @return 修改后的值
     */
    public Object alter(String name, Object value) {
        try {
            this.getField(name).set(this.object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.object;
    }

    /**
     * 获取该字段所有修饰符
     *
     * @param name 字段名
     * @return 修饰符
     */
    public String getModifier(String name) {
        return Modifier.toString(getField(name).getModifiers());
    }
}
