package net.bxx2004.pandalib.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 简单的命令构建类
 */
public abstract class SimpleCommand {
    public CommandSender sender;
    public Command command;
    public String string;
    public String[] args;

    /**
     * 构建一个简单的命令
     *
     * @param plugin 插件
     * @param name   插件名称
     */
    public SimpleCommand(JavaPlugin plugin, String name) {
        SimpleCommandBody object = new SimpleCommandBody(this);
        plugin.getCommand(name).setExecutor(object);
    }

    /**
     * 执行该命令的方法
     */
    abstract public void run();
}

class SimpleCommandBody implements CommandExecutor {
    public Command command;
    private SimpleCommand commanda;

    public SimpleCommandBody(SimpleCommand command) {
        this.commanda = command;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        commanda.sender = commandSender;
        commanda.command = command;
        this.command = command;
        commanda.string = string;
        commanda.args = strings;
        commanda.run();
        return false;
    }
}