package net.bxx2004.pandalib.bukkit.language.component;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.AnimationText;
import net.bxx2004.pandalib.bukkit.language.TextBox;
import net.bxx2004.pandalib.bukkit.language.TextType;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ChatText extends AnimationText {
    private TextBox box;

    public ChatText(String data) {
        this.box = new TextBox("data=" + data.replaceAll("<space>", " "));
    }

    public static List<String> replace(List<String> list) {
        List<String> a = new ArrayList<>();
        for (String b : list) {
            a.add(b.replaceAll("&", "§"));
        }
        return a;
    }

    public static String replace(String s) {
        return s.replaceAll("&", "§");
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
            commandSender.sendMessage(translate(box.getNode("data")));
        }
    }

    @Override
    public TextType type() {
        return TextType.MESSAGE;
    }

    @Override
    public void send(CommandSender sender, AnimationTextType type) {
        switch (type) {
            case WRITE: {
                char[] content = translate(box.getNode("data")).toCharArray();
                new BukkitRunnable() {
                    int i = 0;
                    String m = "";

                    @Override
                    public void run() {
                        m += content[i];
                        for (int i = 0; i < 100; i++) {
                            sender.sendMessage(" ");
                        }
                        PMessage.to(sender, m);
                        i = i + 1;
                        if (i == content.length) {
                            for (int i = 0; i < 100; i++) {
                                sender.sendMessage(" ");
                            }
                            send(sender);
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 5);
                break;
            }
            default:
                Lang.error(type + " 尝试给ChatText赋予不可接受的动画类型,导致消息无法发出", toJSON());
        }
    }

    @Override
    public void send(AnimationTextType type, CommandSender... sender) {
        for (CommandSender commandSender : sender) {
            send(commandSender, type);
        }
    }
}
