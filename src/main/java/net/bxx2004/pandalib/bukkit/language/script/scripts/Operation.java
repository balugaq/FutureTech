package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.command.CommandSender;

@Script
//operate +|-|*|/ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
public class Operation implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "operate";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = ScriptUtil.translateVar(box.args(performer));
        String type = args[0];
        String result = "";
        for (String a : args[1].split(",")) {
            if (type.equals("+")) {
                try {
                    double d = Double.valueOf(a);
                    result = String.valueOf(d + Double.parseDouble(result));
                } catch (Exception e) {
                    result = result + a;
                }
            }
            if (type.equals("-")) {
                result = String.valueOf(Double.valueOf(result) - Double.valueOf(a));
            }
            if (type.equals("*")) {
                result = String.valueOf(Double.valueOf(result) * Double.valueOf(a));
            }
            if (type.equals("/")) {
                result = String.valueOf(Double.valueOf(result) / Double.valueOf(a));
            }
        }
        return result;
    }
}
