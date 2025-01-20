package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptHandler;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.command.CommandSender;

/*
    for (1) {
        s;
        a;
    }
    不允许嵌套!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
@Script
public class For implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "for";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        final int[] t = {0};
        PMath.regx("\\(.*\\)", box.getOrigin(), a -> {
            try {
                t[0] = Integer.parseInt(a.replaceAll("\\(", "").replaceAll("\\)", ""));
            } catch (Exception e) {
                t[0] = (int) ScriptHandler.run(performer, new ScriptBox(a));
            }
        });

        for (int i = 0; i < t[0]; i++) {
            PMath.regx("\\{.*\\}", box.getOrigin(),
                    s -> {
                        String word = s.replaceAll("\\{", "").replaceAll("}", "");
                        if (word.contains(";")) {
                            for (String a : word.split(";")) {
                                ScriptHandler.run(performer, new ScriptBox(a));
                            }
                        } else {
                            ScriptHandler.run(performer, new ScriptBox(word));
                        }
                    }
            );
        }
        return null;
    }
}
