package net.bxx2004.pandalib.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtils {
    /**
     * 根据指定类名获取对象
     *
     * @param className 类名
     * @param args      参数
     * @param objects   参数
     * @return 对象
     */
    public static Object objectFromClass(String className, Class[] args, Object[] objects) {
        try {
            Constructor c = Class.forName(className).getConstructor(args);
            return c.newInstance(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载类
     *
     * @param classPath 类名
     * @return 类
     */
    public static Class getClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得方法
     *
     * @param c          所在类
     * @param methodName 方法名
     * @return 方法
     */
    public static Method getMethod(Class c, String methodName, Class... arms) {
        try {
            if (arms.length <= 0) {
                Method method = c.getDeclaredMethod(methodName);
                return method;
            }
            Method method = c.getDeclaredMethod(methodName, arms);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
