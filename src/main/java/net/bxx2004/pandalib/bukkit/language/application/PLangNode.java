package net.bxx2004.pandalib.bukkit.language.application;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.file.PYml;
import net.bxx2004.pandalib.bukkit.language.abandon.PActionBar;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.language.abandon.PTitle;
import net.bxx2004.pandalibloader.PandaLibPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class PLangNode {
    private File path;
    private PandaLibPlugin plugin;
    private PYml langFile;

    /**
     * 初始化语言节点
     * 约定格式(language/语言文件.yml)
     *
     * @param plugin 插件
     * @param lang   跟随插件语言
     */
    public PLangNode(PandaLibPlugin plugin, String lang) {
        this.plugin = plugin;
        path = new File(plugin.getPath() + "/language");
        if (!path.exists()) {
            path.mkdirs();
        }
        if (lang != null) {
            File file = new File(path.getAbsolutePath() + "/" + lang + ".yml");
            if (file.exists()) {
                langFile = new PYml(file.getAbsolutePath(), false);
            } else {
                PandaLib.saveFileFormPlugin(plugin, "language/" + lang + ".yml", path.getAbsolutePath() + "/" + lang + ".yml");
                langFile = new PYml(file.getAbsolutePath(), false);
            }
        } else {
            File file = new File(path.getAbsolutePath() + "/" + PandaLib.getServerLanguage() + ".yml");
            if (file.exists()) {
                langFile = new PYml(file.getAbsolutePath(), false);
            } else {
                PandaLib.saveFileFormPlugin(plugin, "language/" + PandaLib.getServerLanguage() + ".yml", path.getAbsolutePath() + "/" + PandaLib.getServerLanguage() + ".yml");
                langFile = new PYml(file.getAbsolutePath(), false);
            }
        }
    }

    public void tell(CommandSender target, String node) {
        target.sendMessage(langFile.getString(node).replaceAll("&", "§"));
    }

    public void title(Player target, String node) {
        PTitle.To(target, text(node));
    }

    public void actionbar(Player target, String node) {
        PActionBar.To(target, text(node));
    }

    public String text(String node) {
        return langFile.getString(node).replaceAll("&", "§");
    }

    public String text(String node, PVarChar varChars) {
        return varChars.replace(text(node));
    }

    public List<String> list(String node) {
        return PMessage.replace(langFile.getYaml().getStringList(node));
    }

    public String lang(String node) {
        return text(node);
    }
}
