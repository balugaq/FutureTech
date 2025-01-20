package net.bxx2004.pandalib.bukkit.commands;

import net.bxx2004.pandalib.bukkit.language.abandon.PHelper;
import net.bxx2004.pandalib.bukkit.language.application.PLangNode;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 更友好的命令系统(直接继承)
 */
public abstract class PCommand implements TabExecutor {
    public static HashMap<String, List<SubCommand>> commandMap = new HashMap<>();
    public String name = this.getClass().getAnnotationsByType(BukkitCommand.class)[0].name();
    public String permission = this.getClass().getAnnotationsByType(BukkitCommand.class)[0].permission();
    public String[] aliases = this.getClass().getAnnotationsByType(BukkitCommand.class)[0].aliases();
    public String permissionMessage = this.getClass().getAnnotationsByType(BukkitCommand.class)[0].permissionMessage();
    private CommandSender commandSender;
    private String[] strings;
    private PHelper helper;

    /**
     * 注册命令
     *
     * @param plugin    插件主类
     * @param name      命令名称
     * @param pcommands 命令类
     */
    public static void registerCommand(JavaPlugin plugin, String name, PCommand pcommands) {
        PluginCommand command = null;
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);
            command = c.newInstance(name, plugin);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        command.setExecutor(pcommands);
        command.setTabCompleter(pcommands);
        if (pcommands.permissionMessage != null) {
            command.setPermissionMessage(pcommands.permissionMessage);
        }
        if (pcommands.permission != null) {
            command.setPermission(pcommands.permission);
        }
        if (pcommands.aliases != null) {
            command.setAliases(Arrays.asList(pcommands.aliases));
        }
        try {
            Field f = SimplePluginManager.class.getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap map = (CommandMap) f.get(plugin.getServer().getPluginManager());
            map.register(name, command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void do2(List<SubCommand> psubcommandsList) {
        Iterator iterator = psubcommandsList.iterator();
        while (iterator.hasNext()) {
            SubCommand sub = (SubCommand) iterator.next();
            sub.performCommand(commandSender, strings);
        }
    }

    public abstract boolean run(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

    /**
     * 同CommandExecutor一样
     *
     * @param commandSender 命令发送者
     * @param command       ...
     * @param s             ...
     * @param strings       ...
     * @return ...
     */
    @Override
    public final boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        this.commandSender = commandSender;
        if (permission != null) {
            if (commandSender.hasPermission(permission)) {
                this.strings = strings;
                if (commandMap.get(name) != null && strings.length >= 1) {
                    do2(commandMap.get(name));
                    return false;
                }
            }
        } else {
            this.strings = strings;
            if (commandMap.get(name) != null && strings.length >= 1) {
                do2(commandMap.get(name));
                return false;
            }
        }
        return run(commandSender, command, s, strings);
    }

    /**
     * 同TabExecutor一样
     *
     * @param commandSender 命令发送者
     * @param command       ...
     * @param s             ...
     * @param strings       ...
     * @return ...
     */
    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }

    private final void hookHelper(PLangNode node) {
        HashMap<String, String> map = new HashMap<>();
        if (this.helper != null) {
            StringBuilder subcommanddesc = new StringBuilder();
            for (SubCommand sub : commandMap.get(name)) {
                String usage = sub.usage;
                String description = sub.description;
                if (usage.contains("Node")) {
                    String s = usage.split(":")[1];
                    usage = node.text(s);
                }
                if (description.contains("Node")) {
                    String s = description.split(":")[1];
                    description = node.text(s);
                }
                subcommanddesc.append(usage + "-" + description).append(",");
            }
            String ali = "";
            for (String a : aliases) {
                ali += a + "/";
            }
            ali = ali + ",";
            map.put(name, permission + "," + ali + subcommanddesc);
        }
        if (commandMap.get(name) != null) {
            StringBuilder subcommanddesc = new StringBuilder();
            for (SubCommand sub : commandMap.get(name)) {
                String usage = sub.usage;
                String description = sub.description;
                if (usage.contains("Node")) {
                    String s = usage.split(":")[1];
                    usage = node.text(s);
                }
                if (description.contains("Node")) {
                    String s = description.split(":")[1];
                    description = node.text(s);
                }
                subcommanddesc.append(usage + "-" + description).append(",");
            }
            String ali = "";
            for (String a : aliases) {
                ali += a + "/";
            }
            ali = ali + ",";
            map.put(name, permission + "," + ali + subcommanddesc);
            helper = new PHelper(null, map);
        }
    }

    /**
     * 获取某个指令的所有命令帮助
     *
     * @param node 节点多语言表达式
     * @return 命令帮助
     */
    public final PHelper commandHelp(PLangNode node) {
        hookHelper(node);
        if (strings.length < 1) {
            helper.toPlayerOfKey(commandSender, name, true);
        }
        return helper;
    }
}
