package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.command.CommandSender;

//check a > b
@Script
public class Check implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "check";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = ScriptUtil.translateVar(box.args(performer));
        String a = String.valueOf(args[0]);
        String b = String.valueOf(args[2]);
        switch (args[1]) {
            case ">":
                return a.compareTo(b) > 0;
            case ">=":
                return a.compareTo(b) >= 0;
            case "<":
                return a.compareTo(b) < 0;
            case "<=":
                return a.compareTo(b) <= 0;
            case "==":
                return a.compareTo(b) == 0;
        }
        return false;
    }
}
