package net.bxx2004.pandalib.bukkit.language.abandon;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的消息类
 */
public class PMessage {
    /**
     * 发送一个带点击的消息
     *
     * @param player  玩家
     * @param message 消息
     * @param action  动作
     * @param value   执行的命令
     * @param hover   悬浮信息
     */
    public static void to2(CommandSender player, String message, ClickEvent.Action action, String value, String hover) {
        TextComponent textComponent = new TextComponent();
        textComponent.setText(message.replaceAll("&", "§"));
        textComponent.setClickEvent(new ClickEvent(action, value));
        TextComponent component1 = new TextComponent();
        component1.setText(hover.replaceAll("&", "§"));
        BaseComponent[] component2 = new BaseComponent[]{component1};
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, component2));
        player.spigot().sendMessage(textComponent);
    }

    /**
     * 发送一个彩色消息
     *
     * @param player  玩家
     * @param message 消息 Shift+7为颜色符号
     */
    public static void to(CommandSender player, String message) {
        player.sendMessage(message.replaceAll("&", "§"));
    }

    /**
     * 发送一个列表消息
     *
     * @param player 玩家
     * @param list   列表
     */
    public static void toList(CommandSender player, List list) {
        for (Object st : list) {
            String a = (String) st;
            player.sendMessage(a.replaceAll("&", "§"));
        }
    }

    /**
     * 为集合替换颜色符合
     *
     * @param list 集合
     * @return 替换后的集合
     */
    public static List<String> replace(List<String> list) {
        List<String> a = new ArrayList<>();
        for (String b : list) {
            a.add(b.replaceAll("&", "§"));
        }
        return a;
    }

    /**
     * 为字符串替换颜色符号
     *
     * @param s 字符串
     * @return 替换好的
     * @since 1.4.2
     */
    public static String replace(String s) {
        return s.replaceAll("&", "§");
    }
}
