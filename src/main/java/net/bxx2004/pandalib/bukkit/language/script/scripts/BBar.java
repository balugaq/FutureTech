package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.component.BossBar;
import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

@Script
//bossbar clear id
//bossbar id value color style flag...
public class BBar implements ScriptImpl {
    private static HashMap<String, BossBar> datas = new HashMap<>();

    @Override
    public String scriptIdentifier() {
        return "bossbar";

    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        String[] args = ScriptUtil.translateVar(box.args(performer));
        if (args[0].equalsIgnoreCase("clear")) {
            String id = args[1];
            box.run(performer, runner -> {
                datas.get(id).delete(runner);
            });
        } else {
            String id = args[0];
            String content = args[1];
            BarColor color = BarColor.valueOf(args[2]);
            BarStyle style = BarStyle.valueOf(args[3]);
            BarFlag[] flags = new BarFlag[args[4].split(",").length];
            int i = 0;
            for (String s : args[4].split(",")) {
                flags[i] = BarFlag.valueOf(s);
                i++;
            }
            BossBar bar = new BossBar(content, color, style, flags);
            box.run(performer, runner -> {
                bar.send(runner);
            });
            datas.put(id, bar);
        }
        return null;
    }
}
