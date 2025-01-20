package net.bxx2004.pandalib.java.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 关于方法的反射
 *
 * @since 1.5.2
 */
public class PJMethod {
    //内部方法
    private Method method;
    //方法名
    private String methodName;
    //类
    private Class aClass;
    //参数类
    private Class[] classes;
    //对象
    private Object[] objects;

    /**
     * 构造一个PJMethod来操作方法
     *
     * @param in         所在类
     * @param methodName 方法名
     * @param classes    需传入对象
     */
    @Deprecated
    public PJMethod(Class in, String methodName, Class... classes) {
        this.aClass = in;
        this.methodName = methodName;
        this.classes = classes;
        this.method = ReflectUtils.getMethod(in, methodName, classes);
    }

    /**
     * 构造一个PJMethod来操作方法
     *
     * @param in 所在类
     */
    public PJMethod(Class in) {
        this.aClass = in;
    }

    /**
     * 输入方法名
     *
     * @param methodName 方法名
     * @return 当前对象
     */
    public PJMethod InPutName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * 输入参数
     *
     * @param objects 参数
     * @return 当前对象
     */
    public PJMethod InPutArg(Object... objects) {
        Class[] classes = new Class[objects.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = objects[i].getClass();
        }
        this.objects = objects;
        this.classes = classes;
        return this;
    }

    public Object runIgnoreArgs(Object o) {
        this.method = ReflectUtils.getMethod(aClass, methodName);
        try {
            try {
                this.method.setAccessible(true);
            } catch (Exception e) {
            }
            Object res = method.invoke(o, this.objects);
            return res;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行这个方法
     *
     * @param object 执行对象
     * @return 执行结果
     */
    public Object run(Object object) {
        this.method = ReflectUtils.getMethod(aClass, methodName, classes);
        try {
            try {
                this.method.setAccessible(true);
            } catch (Exception e) {
            }
            Object res = method.invoke(object, this.objects);
            return res;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行这个方法
     *
     * @param object 对象
     * @param args   参数
     * @return 返回结果
     */
    @Deprecated
    public Object runMethod(Object object, Object... args) {
        try {
            this.method.setAccessible(true);
        } catch (Exception e) {
        }
        try {
            Object res = method.invoke(object, args);
            return res;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取其他类中的方法
     *
     * @param methodName 方法名
     * @param classes    参数
     * @return PJMethod对象
     */
    @Deprecated
    public PJMethod reSet(String methodName, Class... classes) {
        this.methodName = methodName;
        this.classes = classes;
        this.method = ReflectUtils.getMethod(this.aClass, methodName, classes);
        try {
            this.method.setAccessible(true);
        } catch (Exception e) {
        }
        return this;
    }

    /**
     * 获取该方法所有修饰符
     *
     * @return 修饰符
     * @since 1.5.2
     */
    public String getModifier() {
        return Modifier.toString(method.getModifiers());
    }
}
