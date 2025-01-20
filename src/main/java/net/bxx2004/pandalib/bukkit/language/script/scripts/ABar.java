package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.AnimationText;
import net.bxx2004.pandalib.bukkit.language.component.ActionBar;
import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.command.CommandSender;

@Script
//actionbar <Animation> <vaule>
public class ABar implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "actionbar";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        switch (box.args(performer)[0].toUpperCase()) {
            case "ONE_BY_ONE": {
                box.run(performer, runner -> {
                    ActionBar bar = new ActionBar(ScriptUtil.translateVar(box.args(performer)[1]));
                    bar.send(AnimationText.AnimationTextType.valueOf(box.args(performer)[0]), runner);
                });
            }
            case "WRITE": {
                box.run(performer, runner -> {
                    ActionBar bar = new ActionBar(ScriptUtil.translateVar(box.args(performer)[1]));
                    bar.send(AnimationText.AnimationTextType.valueOf(box.args(performer)[0]), runner);
                });
            }
            case "FLOWER": {
                box.run(performer, runner -> {
                    ActionBar bar = new ActionBar(ScriptUtil.translateVar(box.args(performer)[1]));
                    bar.send(AnimationText.AnimationTextType.valueOf(box.args(performer)[0]), runner);
                });
            }
            default: {
                box.run(performer, runner -> {
                    ActionBar bar = new ActionBar(ScriptUtil.translateVar(box.args(performer)[0]));
                    bar.send(runner);
                });
            }
        }
        return null;
    }
}
