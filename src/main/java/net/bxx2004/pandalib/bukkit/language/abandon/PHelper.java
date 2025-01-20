package net.bxx2004.pandalib.bukkit.language.abandon;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 一个命令帮助的模板
 */
public class PHelper {
    private HashMap<String, String> map;
    private Plugin plugin;

    /**
     * 构造一个命令帮助信息类
     *
     * @param plugin 插件
     * @param map    命令信息 (key=命令 value=权限,别名,参数-介绍,参数-介绍)
     */
    public PHelper(Plugin plugin, HashMap<String, String> map) {
        this.map = map;
        this.plugin = plugin;
    }

    /**
     * 发送一个模板(灰色)
     *
     * @param player  玩家
     * @param title   插件名称
     * @param message 信息(命令-描述,命令2-描述2)
     */
    public static void To(CommandSender player, String title, String... message) {
        player.sendMessage("§c§l§o" + title.replaceAll("&", "§") + " §f| §dCommandHelper§6§l>>>");
        for (String m : message) {
            String[] a = m.split("[-]");
            TextComponent component = new TextComponent();
            TextComponent component1 = new TextComponent();
            component1.setText(a[1].replaceAll("&", "§"));
            BaseComponent[] component2 = new BaseComponent[]{component1};
            component.setText("§b§k|§b- §7§o" + a[0].replaceAll("&", "§") + " §c§o- §f§o" + a[1].replaceAll("&", "§"));
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + a[0].replaceAll("&", "§")));
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, component2));
            player.spigot().sendMessage(component);
            player.sendMessage("");
        }
    }

    /**
     * 获取Map
     *
     * @return
     */
    public HashMap<String, String> getMap() {
        return map;
    }

    /**
     * 获取插件
     *
     * @return 插件
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * 发送该命令信息
     *
     * @param player 玩家
     * @param key    键
     * @param lang   true为中文
     */
    public void toPlayerOfKey(CommandSender player, String key, boolean lang) {
        if (lang) {
            String[] value = map.get(key).split(",");
            List<String> list = new ArrayList();
            for (int i = 2; i < value.length; i++) {
                list.add(value[i]);
            }
            PMessage.to(player, "");
            PMessage.to(player, "&7&l 命令: &c&l/" + key + " &f[ 别名: &b&L" + value[1] + "&f ] " + "&f[ 权限: &b&L" + value[0] + "&f ]");
            PMessage.to(player, "");
            PMessage.to(player, "    &f&l参数:");
            for (String v : list) {
                String[] vs = v.split("-");
                PMessage.to2(player, "    &7- " + vs[0], ClickEvent.Action.SUGGEST_COMMAND, "/" + key + " " + vs[0], "/" + key + " " + vs[0]);
                PMessage.to(player, "      " + vs[1]);
            }
            PMessage.to(player, "");
        } else {
            String[] value = map.get(key).split(",");
            List<String> list = new ArrayList();
            for (int i = 2; i < value.length; i++) {
                list.add(value[i]);
            }
            PMessage.to(player, "");
            PMessage.to(player, "&7&l Command: &c&l/" + key + " &f[ other: &b&L" + value[1] + " &f] " + "&f[ permission: &b&L" + value[0] + "&f ]");
            PMessage.to(player, "");
            PMessage.to(player, "    &f&lParameters:");
            for (String v : list) {
                String[] vs = v.split("-");
                PMessage.to2(player, "    &7- " + vs[0], ClickEvent.Action.SUGGEST_COMMAND, "/" + key + " " + vs[0], "/" + key + " " + vs[0]);
                PMessage.to(player, "      " + vs[1]);
            }
            PMessage.to(player, "");
        }
    }
}
