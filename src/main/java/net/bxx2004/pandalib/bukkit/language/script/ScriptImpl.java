package net.bxx2004.pandalib.bukkit.language.script;

import org.bukkit.command.CommandSender;

public interface ScriptImpl {
    public String scriptIdentifier();

    public Object scriptRealize(CommandSender performer, ScriptBox box);
}
