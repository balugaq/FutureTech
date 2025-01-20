package net.bxx2004.pandalib.bukkit.language.script;

import net.bxx2004.pandalib.bukkit.manager.Lang;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScriptUtil {
    public static HashMap<String, Object> vars = new HashMap<String, Object>();

    public static boolean compute(CommandSender sender, String terms) {
        String term = terms.replaceAll("\\(", "").replaceAll("\\)", "");
        try {
            if (term.contains("|")) {
                String[] a = term.split("\\|");
                for (String b : a) {
                    boolean result = (boolean) ScriptHandler.run(sender, new ScriptBox(b.trim()));
                    if (result) {
                        return true;
                    }
                }
                return false;
            }
            if (term.contains("&")) {
                String[] a = term.split("&");
                for (String b : a) {
                    boolean result = (boolean) ScriptHandler.run(sender, new ScriptBox(b.trim()));
                    if (!result) {
                        return false;
                    }
                }
                return true;
            }
            boolean result = (boolean) ScriptHandler.run(sender, new ScriptBox(term.trim()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Lang.error("&4条件计算异常", term);
        }
        return false;
    }

    public static String translateVar(String script) {
        List<String> var = new ArrayList<>();
        final String[] result = {script};
        PMath.regx("(?<=\\[)\\S+(?=\\])", script, data -> {
            if (!data.contains("][")) {
                var.add(data);
            } else {
                for (String v : data.split("]\\[")) {
                    var.add(v);
                }
            }
        });
        var.forEach(v -> {
            result[0] = result[0].replaceAll("\\[" + v + "]", String.valueOf(vars.get(v)));
        });
        return result[0];
    }

    public static String[] translateVar(String[] script) {
        String[] r = script;
        int i = 0;
        for (String s : script) {
            r[i] = translateVar(s);
            i++;
        }
        return r;
    }
}
