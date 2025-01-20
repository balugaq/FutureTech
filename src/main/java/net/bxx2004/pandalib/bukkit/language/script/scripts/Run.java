package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptHandler;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

@Script
//run cancel id
//run create [later|timer] time id {s;s;s;s;s;s;s;s;}
public class Run implements ScriptImpl {
    private static HashMap<String, BukkitRunnable> datas = new HashMap<>();

    @Override
    public String scriptIdentifier() {
        return "run";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = ScriptUtil.translateVar(box.args(performer));
        if (args[0].equalsIgnoreCase("cancel")) {
            datas.get(args[1]).cancel();
        }
        if (args[0].equalsIgnoreCase("create")) {
            String method = args[1];
            long time = Long.parseLong(args[2]);
            String id = args[3];
            List<ScriptBox> boxes = box.codeBlock(performer);
            switch (method) {
                case "later": {
                    BukkitRunnable able = new BukkitRunnable() {
                        @Override
                        public void run() {
                            ScriptHandler.runs(performer, boxes);
                        }
                    };
                    datas.put(id, able);
                    able.runTaskLater(PandaLib.initPlugin, time);
                    break;
                }
                case "timer": {
                    BukkitRunnable able = new BukkitRunnable() {
                        @Override
                        public void run() {
                            ScriptHandler.runs(performer, boxes);
                        }
                    };
                    datas.put(id, able);
                    able.runTaskTimer(PandaLib.initPlugin, 0, time);
                    break;
                }
            }
        }
        return null;
    }
}
