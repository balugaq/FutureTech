package net.bxx2004.pandalib.bukkit.language.abandon;

import net.bxx2004.pandalib.PandaLib;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 动作栏工具类
 */
public class PActionBar {
    /**
     * 发送一个ActionBar
     *
     * @param player  玩家
     * @param message 内容
     */
    public static void To(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message.replaceAll("&", "§")));
    }

    /**
     * 发送一个打印特效的ActionBar
     *
     * @param player  玩家
     * @param message 内容
     * @param speed   速度
     */
    public static void To2(Player player, String message, int speed) {
        char[] titles = message.toCharArray();
        new BukkitRunnable() {
            int i = 0;
            String m = "";

            @Override
            public void run() {
                m += titles[i];
                m += m.replaceAll("&", "§") + "_";
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(m));
                i = i + 1;
                if (i == titles.length) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(" "));
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
    }
}
