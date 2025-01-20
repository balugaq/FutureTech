package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptHandler;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.command.CommandSender;

@Script
public class Vars implements ScriptImpl {
    //var a s,a,s
    @Override
    public String scriptIdentifier() {
        return "var";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = box.args(performer);
        String value = ScriptUtil.translateVar(args[1]);
        if (value.startsWith("*")) {
            ScriptUtil.vars.put(args[0], ScriptHandler.run(performer, new ScriptBox(box.getOriginReplace(performer).split("\\*")[1])));
        } else {
            if (value.contains(",")) {
                ScriptUtil.vars.put(args[0], PMath.toStringList(value.split(",")));
            } else {
                ScriptUtil.vars.put(args[0], value);
            }
        }
        return null;
    }
}
