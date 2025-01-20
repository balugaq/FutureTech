package net.bxx2004.pandalib.java.reflect;

import java.lang.reflect.Constructor;

/**
 * @since 1.5.2
 * 关于一个类的反射
 */
public class PJObject {
    private Class clazz;
    private String c;
    private Class[] args;
    private Object[] objects;

    /**
     * 构造一个反射类
     *
     * @param clazz 类名
     */
    public PJObject(Class clazz) {
        this.clazz = clazz;
        this.c = clazz.getName();
    }

    /**
     * 无参构造方法实例化对象
     *
     * @return 对象
     */
    public Object newObject() {
        try {
            return this.clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 有参构造方法实例化对象
     *
     * @param args    参数
     * @param objects 参数
     * @return 对象
     */
    @Deprecated
    public Object newObject(Class[] args, Object[] objects) {
        try {
            this.args = args;
            this.objects = objects;
            Constructor c = this.clazz.getConstructor(args);
            c.setAccessible(true);
            return c.newInstance(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 有参构造方法实例化对象
     *
     * @param args 参数
     * @return 对象
     */
    public Object newObject(Object... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                this.args[i] = args[i].getClass();
            }
            Constructor c = this.clazz.getConstructor(this.args);
            c.setAccessible(true);
            return c.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取PJ变量对象
     *
     * @param isobject 是否为对象形式
     * @return 变量对象
     */
    public PJVariable getPJVariable(boolean isobject) {
        if (isobject) {
            return new PJVariable(newObject(this.args));
        } else {
            return new PJVariable(c);
        }
    }

    /**
     * 获取PJ方法对象
     *
     * @return 方法对象
     */
    public PJMethod getPJMthod() {
        return new PJMethod(clazz);
    }
}
