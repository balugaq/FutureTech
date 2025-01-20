package net.bxx2004.pandalib.bukkit.language.component;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.AnimationText;
import net.bxx2004.pandalib.bukkit.language.TextBox;
import net.bxx2004.pandalib.bukkit.language.TextType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBar extends AnimationText {
    private TextBox box;

    public ActionBar(String data) {
        this.box = new TextBox("data=" + data.replaceAll("<space>", " "));
    }

    @Override
    public void send(CommandSender sender, AnimationTextType type) {
        Player player = (Player) sender;
        String content = box.getNode("data");
        char[] titles;
        if (content.contains("<br>")) {
            titles = content.split("<br>")[1].toCharArray();
        } else {
            titles = content.toCharArray();
        }
        switch (type) {
            case WRITE:
                new BukkitRunnable() {
                    int i = 0;
                    String m = "";

                    @Override
                    public void run() {
                        m += titles[i];
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translate(m)));
                        i = i + 1;
                        if (i == titles.length) {
                            send(player);
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 5);
                break;
            case ONE_BY_ONE:
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        String m = String.valueOf(titles[i]);
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translate(m)));
                        i = i + 1;
                        if (i == titles.length) {
                            send(player);
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 5);
                break;
            case FLOWER:
                int center = titles.length / 2;
                new BukkitRunnable() {
                    int i = 1;
                    String content = String.valueOf(titles[center]);

                    @Override
                    public void run() {
                        try {
                            String a = String.valueOf(titles[center - i]);
                            String b = String.valueOf(titles[center + i]);
                            content = a + content + b;
                            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translate(content)));
                            i = i + 1;
                        } catch (Exception e) {
                            send(player);
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 5);
        }
    }

    @Override
    public void send(AnimationTextType type, CommandSender... sender) {
        for (CommandSender commandSender : sender) {
            send(commandSender, type);
        }
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
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translate(box.getNode("data"))));
        }
    }

    @Override
    public TextType type() {
        return TextType.ACTION_BAR;
    }
}
