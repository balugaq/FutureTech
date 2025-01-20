package net.bxx2004.pandalib.bukkit.task.data;

import net.bxx2004.pandalib.PandaLib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ConfigTask {
    {
        Class clazz = this.getClass();
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            m.setAccessible(true);
            Config anno = m.getAnnotation(Config.class);
            if (anno != null) {
                String node = anno.node();
                String data = anno.data();
                if (PandaLib.initPlugin.getConfig().get(node).toString().equals(data)) {
                    try {
                        m.invoke(this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
