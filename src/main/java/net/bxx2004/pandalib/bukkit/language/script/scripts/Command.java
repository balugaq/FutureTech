package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@Script
//command op|console|player content
public class Command implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "command";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = ScriptUtil.translateVar(box.args(performer));
        String way = args[0];
        String command = "";
        for (int a = 1; a < args.length; a++) {
            command += (args[a] + " ");
        }
        if (way.equalsIgnoreCase("player")) {
            String finalCommand = command;
            box.run(performer, runner -> {
                Bukkit.dispatchCommand(runner, finalCommand);
            });
        }
        if (way.equalsIgnoreCase("op")) {
            String finalCommand1 = command;
            box.run(performer, runner -> {
                if (!runner.isOp()) {
                    runner.setOp(true);
                    Bukkit.dispatchCommand(runner, finalCommand1);
                    runner.setOp(false);
                } else {
                    Bukkit.dispatchCommand(runner, finalCommand1);
                }
            });
        }
        if (way.equalsIgnoreCase("console")) {
            String finalCommand2 = command;
            box.run(performer, runner -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand2);
            });
        }
        return null;
    }
}
