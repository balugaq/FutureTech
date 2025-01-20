package net.bxx2004.pandalib.bukkit.language.script.scripts;

import net.bxx2004.pandalib.bukkit.language.component.ChatText;
import net.bxx2004.pandalib.bukkit.language.script.Script;
import net.bxx2004.pandalib.bukkit.language.script.ScriptBox;
import net.bxx2004.pandalib.bukkit.language.script.ScriptImpl;
import net.bxx2004.pandalib.bukkit.language.script.ScriptUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@Script
//print value
public class Print implements ScriptImpl {
    @Override
    public String scriptIdentifier() {
        return "print";
    }

    @Override
    public Object scriptRealize(CommandSender performer, ScriptBox box) {
        ChatText text = new ChatText(ScriptUtil.translateVar(box.args(performer)[0]));
        text.send(Bukkit.getConsoleSender());
        return null;
    }
}
