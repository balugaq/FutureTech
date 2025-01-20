package net.bxx2004.pandalibloader;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import net.bxx2004.pandalib.bukkit.util.PDownLoad;
import net.bxx2004.pandalib.bukkit.util.PMath;
import net.bxx2004.pandalib.bukkit.util.PPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static net.bxx2004.pandalibloader.ServerUtils.get256;

/**
 * 继承
 */
public abstract class BukkitPlugin extends JavaPlugin implements PandaLibPlugin<BukkitPlugin>, SafetyPlugin {
    private final String packageName = this.getClass().getAnnotation(Path.class).pack();
    private final BukkitPlugin plugin;

    {
        plugin = this;
        //Library
        PandaLib.init(this);
        if (this.verify() != null) {
            SafetyPluginMessage<BukkitPlugin> message = this.verify();
            if (getAllUsers(message.getUrl()).contains(getCode())) {
                Lang.print("§f- §7插件 §f" + this.getName() + " §7正在加载,请稍后...");
                Lang.print("&e[" + message.getPandaLibPlugin().getPlugin().getName() + "&e] &a认证成功,欢迎使用, 机器码:" + getCode());
            } else {
                Lang.print("&e[" + message.getPandaLibPlugin().getPlugin().getName() + "&e] &c您尚未取得该插件的使用权, 机器码:" + getCode());
                Bukkit.shutdown();
            }
        } else {
            Lang.print("§f- §7插件 §f" + this.getName() + " §7正在加载,请稍后...");
        }
        if (getClass().getAnnotation(KotlinPlugin.class) != null) {
            File file = new File(plugin.getPath().split("plugins")[0] + "server/libs");
            if (!file.exists()) {
                file.mkdirs();
            }

            PMath.toStringList("kotlin-stdlib.jar", "kotlin-reflect.jar", "kotlin-test.jar").forEach(
                    s -> {
                        File jarLib = new File(file.getAbsolutePath() + "/" + s);
                        if (jarLib.exists()) {
                            Lang.print(plugin.getPlugin(), "正在加载资源: &f" + jarLib.getName());
                        } else {
                            String cloudurl = "http://101.43.156.208/lib/" + s;
                            PDownLoad d = new PDownLoad(cloudurl, jarLib.getAbsolutePath());
                            d.start(false);
                            jarLib = new File(file.getAbsolutePath() + "/" + s);
                            Lang.print(plugin.getPlugin(), "正在加载资源: &f" + jarLib.getName());
                        }
                    }
            );
        }
    }

    public BukkitPlugin() {

    }

    protected BukkitPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    public final Metrics bstats(int id) {
        return new Metrics(plugin, id);
    }

    public String getPack() {
        return null;
    }

    public String getAuthName() {
        if (this.getClass().getSimpleName().equals(getName())) {
            return this.getName();
        } else {
            return "Sorry,You don't change plugin name.";
        }
    }

    @Override
    public InputStream getJarFile(String name) {
        return this.getResource(name);
    }

    @Override
    public String getPath() {
        return this.getDataFolder().getAbsolutePath();
    }

    @Override
    public SafetyPluginMessage verify() {
        return null;
    }

    @Override
    public BukkitPlugin getPlugin() {
        return this;
    }

    @Override
    protected File getFile() {
        return super.getFile();
    }

    @Override
    public FileConfiguration getConfig() {
        return super.getConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
    }

    @Override
    public void saveConfig() {
        super.saveConfig();
    }

    @Override
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        super.saveResource(resourcePath, replace);
    }

    @Override
    public InputStream getResource(String filename) {
        return super.getResource(filename);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return super.onTabComplete(sender, command, alias, args);
    }

    @Override
    public PluginCommand getCommand(String name) {
        return super.getCommand(name);
    }

    public abstract void start();

    public abstract void end();

    public abstract void load();

    @Override
    public final void onEnable() {
        start();
        super.onEnable();
    }

    @Override
    public final void onDisable() {
        end();
        super.onDisable();
    }

    @Override
    public final void onLoad() {
        load();
        super.onLoad();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return super.getDefaultWorldGenerator(worldName, id);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<String> getAllUsers(URL url) {
        List<String> list = new ArrayList<>();
        try {
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            Iterator<String> i = br.lines().iterator();
            while (i.hasNext()) {
                list.add(i.next());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getCode() {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            return get256(serial);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final void startMessage(boolean ADSupport) {
        Lang.print("========== 插件启动日志 开始 ==========");
        Lang.print("     ===== 插件信息 =====");
        Lang.print("插件作者: " + getDescription().getAuthors());
        Lang.print("插件主包: " + packageName);
        Lang.print("插件版本: " + getDescription().getVersion());
        Lang.print("内核版本: " + PandaLib.VERSION);
        if (ADSupport) {
            Lang.print("提供支持: PandaLib 强力驱动");
        }
        Lang.print("     ===== 插件联动 =====");
        for (String name : getDescription().getSoftDepend()) {
            if (PPlugin.exist(name)) {
                Lang.print("已经成功与插件 §f" + name + "§7 联动");
            }
        }
        Lang.print("========== 插件启动日志 结束 ==========");
    }
}