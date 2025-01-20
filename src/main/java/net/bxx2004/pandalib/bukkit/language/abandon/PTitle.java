package net.bxx2004.pandalib.bukkit.language.abandon;

import net.bxx2004.pandalib.PandaLib;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 一个Title标题工具类
 */
public class PTitle {

    /**
     * 发送一个标题
     *
     * @param player  玩家
     * @param message 内容
     */
    public static void To(Player player, String message) {
        if (message.contains("&nbsp")) {
            String[] me = message.split("&nbsp");
            player.sendTitle(me[0].replaceAll("&", "§"), me[1].replaceAll("&", "§"), 10, 70, 20);
        } else {
            player.sendTitle(" ", message.replaceAll("&", "§"), 10, 70, 20);
        }
    }

    /**
     * 发送一个带书写特效的标题(一次仅可以发送一个标题)
     *
     * @param type    类型
     * @param speed   速度
     * @param player  玩家
     * @param message 内容
     */
    public static void ToWithWrite(PTitleType type, int speed, Player player, String message) {
        if (type == PTitleType.MAIN_TITLE) {
            char[] titles = message.toCharArray();
            new BukkitRunnable() {
                int i = 0;
                String m = "";

                @Override
                public void run() {
                    m += titles[i];
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                    player.sendTitle(m.replaceAll("&", "§") + "_", " ", 1, 70, 10);
                    i = i + 1;
                    if (i == titles.length) {
                        player.sendTitle("", "", 1, 1, 1);
                        player.sendTitle(m.replaceAll("&", "§"), "", 1, 70, 10);
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
        if (type == PTitleType.SUB_TITLE) {
            char[] titles = message.toCharArray();
            new BukkitRunnable() {
                int i = 0;
                String m = "";

                @Override
                public void run() {
                    m += titles[i];
                    player.sendTitle(" ", m.replaceAll("&", "§") + "_", 1, 70, 10);
                    i = i + 1;
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                    if (i == titles.length) {
                        player.sendTitle(" ", "", 1, 1, 1);
                        player.sendTitle(" ", m.replaceAll("&", "§"), 1, 70, 10);
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
    }

    public static void ToWithDr(Player player, String message) {
        if (message.contains("&nbsp")) {
            String[] me = message.split("&nbsp");
            player.sendTitle("§a§l§m--[|§r" + me[0].replaceAll("&", "§") + "§a§l§m|]--", me[1].replaceAll("&", "§"), 2, 70, 20);
        } else {
            player.sendTitle("§a§l§m--[|§r\" + message.replaceAll(\"&\", \"§\") + \"§a§l§m|]--", "", 2, 70, 20);
        }
    }

    /**
     * 标题类型
     */
    public enum PTitleType {
        /**
         * 主标题
         */
        MAIN_TITLE,
        /**
         * 副标题
         */
        SUB_TITLE
    }
}
