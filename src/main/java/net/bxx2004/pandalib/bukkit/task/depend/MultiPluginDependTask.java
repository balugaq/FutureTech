package net.bxx2004.pandalib.bukkit.task.depend;

import net.bxx2004.pandalib.bukkit.util.PPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class MultiPluginDependTask {
    {
        Class clazz = this.getClass();
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            m.setAccessible(true);
            Depend anno = m.getAnnotation(Depend.class);
            if (anno != null) {
                String name = anno.name();
                String version = anno.version();
                boolean async = anno.asynchronous();
                if (PPlugin.exist(name)) {
                    if (version.equals("all")) {
                        try {
                            if (async) {
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            m.invoke(this);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.runTaskAsynchronously(PPlugin.getPlugin(name));
                            } else {
                                m.invoke(this);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (PPlugin.getPlugin(name).getDescription().getVersion().equals(version)) {
                            try {
                                if (async) {
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                m.invoke(this);
                                            } catch (IllegalAccessException e) {
                                                e.printStackTrace();
                                            } catch (InvocationTargetException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }.runTaskAsynchronously(PPlugin.getPlugin(name));
                                } else {
                                    m.invoke(this);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (name.equals("default")) {
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
