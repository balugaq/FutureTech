package net.bxx2004.pandalib.bukkit.language.script;

import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ScriptBox {
    private String origin;
    private String identifier;

    public ScriptBox(String origin) {
        this.origin = origin;
        this.identifier = origin.trim().split(" ")[0];
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getOrigin() {
        return origin.replaceAll("  ", "")
                .replaceAll("\\\n", "").trim();
    }

    public String[] args(CommandSender sender) {
        if (sender == null) {
            String[] args;
            if (hasTarget()) {
                args = getOrigin().split("-target:")[0].split(" ");
            } else {
                args = getOrigin().split(" ");
            }
            String[] args1 = new String[args.length - 1];
            for (int i = 0; i < args.length; i++) {
                if (i >= args1.length) {
                    continue;
                } else {
                    args1[i] = args[i + 1];
                }
            }
            return args1;
        } else {
            String[] args;
            if (hasTarget()) {
                args = getOriginReplace(sender).split("-target:")[0].split(" ");
            } else {
                args = getOriginReplace(sender).split(" ");
            }
            String[] args1 = new String[args.length - 1];
            for (int i = 0; i < args.length; i++) {
                if (i >= args1.length) {
                    continue;
                } else {
                    args1[i] = args[i + 1];
                }
            }
            return args1;
        }
    }

    public OfflinePlayer getTarget() {
        return Bukkit.getOfflinePlayer(getOrigin().split("-target:")[1]);
    }

    public boolean hasTarget() {
        return getOrigin().contains("-target:");
    }

    public List<ScriptBox> codeBlock(CommandSender sender) {
        List<ScriptBox> bodies = new ArrayList<>();
        PMath.regx("\\{.*\\}", getOriginReplace(sender), body -> {
            String a = body.replaceAll("\\{", "").replaceAll("}", "");
            for (String s : a.split(";")) {
                bodies.add(new ScriptBox(s));
            }
        });
        return bodies;
    }

    public String getOriginReplace(CommandSender sender) {
        if (hasTarget()) {
            return getOrigin().replaceAll("@sender", sender.getName()).replaceAll("@target", getTarget().getName());
        } else {
            return getOrigin().replaceAll("@sender", sender.getName());
        }
    }

    public void run(CommandSender sender, Consumer<CommandSender> consumer) {
        if (hasTarget()) {
            consumer.accept((CommandSender) getTarget());
        } else {
            consumer.accept(sender);
        }
    }
}
