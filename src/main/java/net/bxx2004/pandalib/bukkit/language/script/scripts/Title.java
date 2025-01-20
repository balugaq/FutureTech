package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.AnimationText;
import net.bxx2004.pandalib.bukkit.language.TextType;
import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.command.CommandSender;

@Script
//title <Type> <Animation|Value> <value>
public class Title implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "title";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        TextType type = TextType.valueOf(box.args(performer)[0]);
        String animation = box.args(performer)[1];
        switch (animation.toUpperCase()) {
            case "ONE_BY_ONE": {
                box.run(performer, runner -> {
                    net.bxx2004.pandalib.bukkit.language.component.Title t = new net.bxx2004.pandalib.bukkit.language.component.Title(type, ScriptUtil.translateVar(box.args(performer)[2]));
                    t.send(runner, AnimationText.AnimationTextType.valueOf(animation));
                });
            }
            case "FLOWER": {
                box.run(performer, runner -> {
                    net.bxx2004.pandalib.bukkit.language.component.Title t = new net.bxx2004.pandalib.bukkit.language.component.Title(type, ScriptUtil.translateVar(box.args(performer)[2]));
                    t.send(runner, AnimationText.AnimationTextType.valueOf(animation));
                });
            }
            case "WRITE": {
                box.run(performer, runner -> {
                    net.bxx2004.pandalib.bukkit.language.component.Title t = new net.bxx2004.pandalib.bukkit.language.component.Title(type, ScriptUtil.translateVar(box.args(performer)[2]));
                    t.send(runner, AnimationText.AnimationTextType.valueOf(animation));
                });
            }
            default: {
                box.run(performer, runner -> {
                    net.bxx2004.pandalib.bukkit.language.component.Title t = new net.bxx2004.pandalib.bukkit.language.component.Title(type, ScriptUtil.translateVar(animation));
                    t.send(runner);
                });
            }
        }
        return null;
    }
}
