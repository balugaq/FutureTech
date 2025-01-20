package net.bxx2004.pandalib.bukkit.commands;

import org.bukkit.command.CommandSender;

/**
 * 子命令抽象类
 */
public abstract class SubCommand {
    public String usage;
    public String description;

    /**
     * 子命令执行方法
     *
     * @param sender  命令发送者
     * @param strings 参数
     * @return ...
     */
    public abstract boolean performCommand(CommandSender sender, String[] strings);
}
