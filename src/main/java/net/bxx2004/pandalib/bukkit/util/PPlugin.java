package net.bxx2004.pandalib.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理插件的工具类
 */
public class PPlugin {
    private Plugin plugin;

    /**
     * 构造一个插件管理对象
     *
     * @param pluginName 插件名称
     */
    public PPlugin(String pluginName) {
        this.plugin = Bukkit.getPluginManager().getPlugin(pluginName);
    }

    /**
     * 构造一个插件管理对象
     *
     * @param plugin 插件名称
     */
    public PPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * 获取某个插件的详细信息
     *
     * @param pluginName 插件名称
     * @return 某个插件的详细信息
     */
    public static List<String> getPluginInfo(String pluginName) {
        PPlugin p = new PPlugin(pluginName);
        List<String> list = new ArrayList<>();
        list.add("§c§l§o" + p.getName() + "§f§l§o>>>");
        list.add("§b§k|§r§b- §7插件主类: §f" + p.getMain());
        list.add("§b§k|§r§b- §7插件版本: §f" + p.getVersion());
        list.add("§b§k|§r§b- §7插件作者: §f" + p.getAuthors());
        list.add("§b§k|§r§b- §7注册权限: §f");
        for (String s : p.getPermissions()) {
            list.add("    " + s);
        }
        list.add("§b§k|§r§b- §7注册指令: §f");
        for (String s : p.getCommands()) {
            list.add("    " + s);
        }
        list.add("§b§k|§r§b- §7注册事件: §f");
        for (String s : p.getListener()) {
            list.add("    " + s);
        }
        return list;
    }

    /**
     * 获取某个插件实例
     *
     * @param pluginName 插件名称
     * @return Plugin
     */
    public static Plugin getPlugin(String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName);
    }

    /**
     * 加载某个文件为插件
     *
     * @param path 文件路径
     */
    public static void enablePlugin(String path) {
        try {
            Bukkit.getPluginManager().loadPlugin(new File(path));
        } catch (InvalidPluginException e) {
            e.printStackTrace();
        } catch (InvalidDescriptionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否存在插件
     *
     * @param name 插件名
     * @return 0
     */
    public static boolean exist(String name) {
        return Bukkit.getPluginManager().getPlugin(name) != null;
    }

    /**
     * 从某个目录下加载所有插件
     *
     * @param dir 插件目录
     */
    public static void enablePlugins(String dir) {
        Bukkit.getPluginManager().loadPlugins(new File(dir));
    }

    /**
     * 卸载这个插件
     */
    public void uninstall() {
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        HandlerList.unregisterAll(plugin);
    }

    /**
     * 启用这个插件
     */
    public void install() {
        Bukkit.getServer().getPluginManager().enablePlugin(plugin);
    }

    /**
     * 重新载入这个插件
     */
    public void reload() {
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        HandlerList.unregisterAll(plugin);
        Bukkit.getServer().getPluginManager().enablePlugin(plugin);
    }

    /**
     * 获取这个插件的作者
     *
     * @return 作者
     */
    public String getAuthors() {
        StringBuffer a = new StringBuffer();
        for (String s : plugin.getDescription().getAuthors()) {
            a.append(s + ", ");
        }
        return a.toString();
    }

    /**
     * 获取这个插件的主类
     *
     * @return 主类
     */
    public String getMain() {
        return plugin.getDescription().getMain();
    }

    /**
     * 获取这个插件插件名称
     *
     * @return 名称
     */
    public String getName() {
        return plugin.getName();
    }

    /**
     * 获取这个插件的Config配置文件
     *
     * @return 配置文件
     */
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    /**
     * 获取这个插件的版本
     *
     * @return 插件版本
     */
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    /**
     * 返回这个插件对象
     *
     * @return 插件
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * 获取这个插件注册的指令
     *
     * @return 指令
     */
    public List<String> getCommands() {
        List<String> list = new ArrayList<>();
        for (String s : plugin.getDescription().getCommands().keySet()) {
            list.add(s);
        }
        return list;
    }

    /**
     * 获取这个插件的已注册权限
     *
     * @return 插件已注册权限
     */
    public List<String> getPermissions() {
        List<String> list = new ArrayList<>();
        for (Permission permission : plugin.getDescription().getPermissions()) {
            for (String s : permission.getChildren().keySet()) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * 注销该插件所有的监听器事件
     */
    public void unHookListener() {
        HandlerList.unregisterAll(plugin);
    }

    /**
     * 获取插件已注册监听器
     *
     * @return 已注册监听器
     */
    public List<String> getListener() {
        List<String> list = new ArrayList<>();
        for (RegisteredListener listener : HandlerList.getRegisteredListeners(plugin)) {
            list.add(listener.getListener().toString());
        }
        return list;
    }
}
