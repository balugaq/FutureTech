package net.bxx2004.pandalib.bukkit.manager;

import net.bxx2004.pandalib.PandaLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Lang {
    private String prefix;
    private Plugin plugin;

    /**
     * 构造一个消息对象
     *
     * @param plugin 前缀
     */
    @Deprecated
    public Lang(Plugin plugin) {
        this.plugin = plugin;
        this.prefix = "§f[ §b" + plugin.getName() + " §f]§r";
    }

    public static void print(String message) {
        Bukkit.getConsoleSender().sendMessage("§b[§f " + PandaLib.initPlugin.getName() + " §b] §f- §7" + message.replaceAll("&", "§"));
    }

    public static void print(Plugin plugin, String message) {
        Bukkit.getConsoleSender().sendMessage("§b[§f " + plugin.getName() + " §b] §f- §7" + message.replaceAll("&", "§"));
    }

    public static void error(String type, String... message) {
        Bukkit.getConsoleSender().sendMessage("§b[§c " + PandaLib.initPlugin.getName() + " §b] §f- §c错误: " + type.replaceAll("&", "§"));
        for (String s : message) {
            Bukkit.getConsoleSender().sendMessage("§b[§c " + PandaLib.initPlugin.getName() + " §b] §f- §c" + s.replaceAll("&", "§"));
        }
    }

    public static void error(String type, List<String> message) {
        Bukkit.getConsoleSender().sendMessage("§b[§c " + PandaLib.initPlugin.getName() + " §b] §f- §c错误: " + type.replaceAll("&", "§"));
        for (String s : message) {
            Bukkit.getConsoleSender().sendMessage("§b[§c " + PandaLib.initPlugin.getName() + " §b] §f- §c" + s.replaceAll("&", "§"));
        }
    }

    /**
     * 输出插件启动消息
     */
    @Deprecated
    public void printEnable() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String a = format.format(date);
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §ais Runnig...");
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f插件作者: §b" + plugin.getDescription().getAuthors().get(0));
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f插件版本: §b" + plugin.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f当前时间: §b" + a);
    }

    /**
     * 输出插件关闭消息
     */
    @Deprecated
    public void printDisable() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String a = format.format(date);
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §cis Shutdown...");
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f插件作者: §b" + plugin.getDescription().getAuthors().get(0));
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f插件版本: §b" + plugin.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§6┃ §r" + prefix + " §f当前时间: §b" + a);
    }

    /**
     * 输出一条消息
     *
     * @param message 消息
     */
    @Deprecated
    public void out(String message) {
        Bukkit.getConsoleSender().sendMessage(this.prefix + " " + message.replaceAll("&", "§"));
    }

    /**
     * 输出一条错误消息
     *
     * @param message 消息
     */
    @Deprecated
    public void outError(String message) {
        Bukkit.getConsoleSender().sendMessage(this.prefix + " §c[ERROR]§r " + message.replaceAll("&", "§"));
    }

    /**
     * 输出一条成功消息
     *
     * @param message 消息
     */
    @Deprecated
    public void outSuccess(String message) {
        Bukkit.getConsoleSender().sendMessage(this.prefix + " §a[SUCCESS]§r " + message.replaceAll("&", "§"));
    }

    /**
     * 输出一条警告消息
     *
     * @param message 消息
     */
    @Deprecated
    public void outWarn(String message) {
        Bukkit.getConsoleSender().sendMessage(this.prefix + " §e[WARN]§r " + message.replaceAll("&", "§"));
    }
}
