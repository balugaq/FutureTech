package net.bxx2004.pandalib.bukkit.language;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Text {
    public static final Pattern HEX_PATTERN = Pattern.compile("&#(\\w{5}[0-9a-f])");

    public static boolean checkTextType(TextType type, TextType... acceptTypes) {
        for (TextType acceptType : acceptTypes) {
            if (acceptType.equals(type)) {
                return false;
            }
        }
        return true;
    }

    public static Text ofYML(String data) {
        BufferedReader reader = new BufferedReader(new StringReader(data));
        FileConfiguration yml = YamlConfiguration.loadConfiguration(reader);
        return null;
    }

    public static String translate(String data) {
        Matcher matcher = HEX_PATTERN.matcher(data);
        StringBuffer buffer = new StringBuffer();

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    abstract public String toYAML();

    abstract public String toJSON();

    abstract public void send(CommandSender... target);

    abstract public TextType type();
}
