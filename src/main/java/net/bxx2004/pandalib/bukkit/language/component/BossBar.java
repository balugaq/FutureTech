package net.bxx2004.pandalib.bukkit.language.component;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.Text;
import net.bxx2004.pandalib.bukkit.language.TextBox;
import net.bxx2004.pandalib.bukkit.language.TextType;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBar extends Text {
    private org.bukkit.boss.BossBar bar;
    private TextBox box;

    public BossBar(String data, BarColor color, BarStyle style, BarFlag... flags) {
        this.bar = Bukkit.createBossBar(translate(data), color, style, flags);
        bar.setProgress(1.00);
        this.box = new TextBox("data=" + data.replaceAll("<space>", " "));
    }

    @Override
    public String toYAML() {
        return "Text:\n" +
                "  type: \"" + type() + "\"\n" +
                "  box: \"" + box.origin() + "\"";
    }

    @Override
    public String toJSON() {
        return "{\n" +
                "  \"Text\": {\n" +
                "    \"type\" : \"" + type() + "\",\n" +
                "    \"box\" : \"" + box.origin() + "\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    public void send(CommandSender... target) {
        for (CommandSender commandSender : target) {
            Player player = (Player) commandSender;
            bar.addPlayer(player);
        }
    }

    public void send(double time, CommandSender... target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                org.bukkit.boss.BossBar ab = bar;
                if (ab.getProgress() <= 0.00) {
                    ab.removeAll();
                    cancel();
                }
                ab.setProgress(ab.getProgress() - (time / 0.01));
                for (CommandSender commandSender : target) {
                    Player player = (Player) commandSender;
                    ab.addPlayer(player);
                }
            }
        }.runTaskTimer(PandaLib.initPlugin, 0, 20);
    }

    public void delete(CommandSender... target) {
        for (CommandSender commandSender : target) {
            Player player = (Player) commandSender;
            bar.removePlayer(player);
        }
    }

    @Override
    public TextType type() {
        return TextType.BOSS_BAR;
    }
}
