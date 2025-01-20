package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptHandler;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/*
    if (条件) {

    } else {

    }
    不允许嵌套!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
@Script
public class IfAndElse implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "if";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String word = box.getOrigin();
        List<String> bodies = new ArrayList<>();
        PMath.regx("\\{.*\\}", word, body -> {
            for (String s : body.split("else")) {
                bodies.add(s);
            }
        });
        PMath.regx("\\(.*\\)", word, term -> {
            if (ScriptUtil.compute(performer, term)) {
                String wa = bodies.get(0).replaceAll("\\{", "").replaceAll("}", "");
                if (wa.contains(";")) {
                    for (String s : wa.split(";")) {
                        ScriptHandler.run(performer, new ScriptBox(s));
                    }
                } else {
                    ScriptHandler.run(performer, new ScriptBox(wa));
                }
            } else {
                String wa = bodies.get(1).replaceAll("\\{", "").replaceAll("}", "");
                if (wa.contains(";")) {
                    for (String s : wa.split(";")) {
                        ScriptHandler.run(performer, new ScriptBox(s));
                    }
                } else {
                    ScriptHandler.run(performer, new ScriptBox(wa));
                }
            }
        });
        return true;
    }
}