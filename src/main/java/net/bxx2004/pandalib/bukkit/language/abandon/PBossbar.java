package net.bxx2004.pandalib.bukkit.language.abandon;

import net.bxx2004.pandalib.PandaLib;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 关于血条显示的工具
 */
public class PBossbar {
    /**
     * 发送一个普普通通的血条
     *
     * @param player  玩家
     * @param color   颜色
     * @param style   样式
     * @param message 信息
     * @param time    显示时间
     * @return 血条
     */
    public static BossBar to(Player player, BarColor color, BarStyle style, String message, int time) {
        BossBar bar = Bukkit.createBossBar(message, color, style);
        bar.addPlayer(player);
        if (time != -1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    bar.removePlayer(player);
                }
            }.runTaskLater(PandaLib.initPlugin, time * 20);
        }
        return bar;
    }

    /**
     * 发送一个随着时间减少的bar(类似进度条)
     *
     * @param player  玩家
     * @param color   颜色
     * @param style   样式
     * @param message 信息
     * @param time    持续时长
     * @return 血条
     */
    public static BossBar to2(Player player, BarColor color, BarStyle style, String message, double time) {
        BossBar bar = Bukkit.createBossBar(message, color, style);
        bar.addPlayer(player);
        final double[] a = {time};
        if (time != -1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (a[0] <= 0.00) {
                        bar.removePlayer(player);
                        cancel();
                    }
                    bar.setProgress(a[0]);
                    a[0] = a[0] - 0.01;
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 20);
        }
        return bar;
    }

}
