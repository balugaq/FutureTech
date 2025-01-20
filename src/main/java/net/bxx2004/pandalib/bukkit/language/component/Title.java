package net.bxx2004.pandalib.bukkit.language.component;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.AnimationText;
import net.bxx2004.pandalib.bukkit.language.TextBox;
import net.bxx2004.pandalib.bukkit.language.TextType;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Title extends AnimationText {
    private TextType type;
    private TextBox box;

    public Title(TextType type, String content) {
        box = new TextBox("data=" + content.replaceAll("<space>", " ") + ";input=20;stay=70;out=20");
        if (checkTextType(type, TextType.SUB_TITLE, TextType.TITLE, TextType.MAIN_TITLE)) {
            Lang.error(type + " 尝试给Title赋予不可接受的类型,导致消息无法发出", toJSON());
        } else {
            this.type = type;
        }
    }

    public Title(TextType type, String content, int input, int stay, int out) {
        box = new TextBox("data=" + content.replaceAll("<space>", " ") + ";input=" + input + ";stay=" + stay + ";out=" + out);
        if (checkTextType(type, TextType.SUB_TITLE, TextType.TITLE, TextType.MAIN_TITLE)) {
            Lang.error(type + " 尝试给Title赋予不可接受的类型,导致消息无法发出", toJSON());
        } else {
            this.type = type;
        }
    }

    @Override
    public void send(CommandSender sender, AnimationText.AnimationTextType atype) {
        Player player = (Player) sender;
        String content = box.getNode("data");
        char[] titles;
        if (content.contains("<br>")) {
            titles = content.split("<br>")[1].toCharArray();
        } else {
            titles = content.toCharArray();
        }
        switch (atype) {
            case WRITE:
                new BukkitRunnable() {
                    int i = 0;
                    String m = "";

                    @Override
                    public void run() {
                        m += titles[i];
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 5, 5);
                        switch (type) {
                            case SUB_TITLE:
                            case TITLE:
                                player.sendTitle(" ", m.replaceAll("&", "§") + "_", 1, 20, 1);
                                break;
                            case MAIN_TITLE:
                                player.sendTitle(m.replaceAll("&", "§") + "_", " ", 1, 20, 1);
                                break;
                        }
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
                        switch (type) {
                            case TITLE:
                            case SUB_TITLE:
                                player.sendTitle(" ", m.replaceAll("&", "§"), 1, 20, 1);
                                break;
                            case MAIN_TITLE:
                                player.sendTitle(m.replaceAll("&", "§"), " ", 1, 20, 1);
                                break;
                        }
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
                            switch (type) {
                                case TITLE:
                                case SUB_TITLE:
                                    player.sendTitle(" ", translate(content), 1, 20, 1);
                                    break;
                                case MAIN_TITLE:
                                    player.sendTitle(translate(content), " ", 1, 20, 1);
                                    break;
                            }
                            String a = String.valueOf(titles[center - i]);
                            String b = String.valueOf(titles[center + i]);
                            content = a + content + b;
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
    public void send(AnimationTextType atype, CommandSender... sender) {
        for (CommandSender commandSender : sender) {
            send(commandSender, atype);
        }
    }

    @Override
    public void send(CommandSender... target) {
        Arrays.stream(target).forEach(s -> {
            Player player = (Player) s;
            switch (type) {
                case TITLE:
                    player.sendTitle(translate(box.getNode("data").split("<br>")[0]),
                            translate(box.getNode("data").split("<br>")[1]),
                            Integer.parseInt(box.getNode("input")),
                            Integer.parseInt(box.getNode("stay")),
                            Integer.parseInt(box.getNode("out"))
                    );
                    break;
                case MAIN_TITLE:
                    player.sendTitle(translate(box.getNode("data")),
                            " ",
                            Integer.parseInt(box.getNode("input")),
                            Integer.parseInt(box.getNode("stay")),
                            Integer.parseInt(box.getNode("out"))
                    );
                    break;
                case SUB_TITLE:
                    player.sendTitle(" ",
                            translate(box.getNode("data")),
                            Integer.parseInt(box.getNode("input")),
                            Integer.parseInt(box.getNode("stay")),
                            Integer.parseInt(box.getNode("out"))
                    );
                    break;
            }
        });
    }

    @Override
    public String toYAML() {
        return "Text:\n" +
                "  type: \"" + type + "\"\n" +
                "  box: \"" + box.origin() + "\"";
    }

    @Override
    public String toJSON() {
        return "{\n" +
                "  \"Text\": {\n" +
                "    \"type\" : \"" + type + "\",\n" +
                "    \"box\" : \"" + box.origin() + "\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    public TextType type() {
        return type;
    }
}
