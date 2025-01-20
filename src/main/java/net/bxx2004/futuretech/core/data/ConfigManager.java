package net.bxx2004.futuretech.core.data;

import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.pandalib.bukkit.file.PYml;
import net.bxx2004.pandalib.bukkit.language.application.PLangNode;

public class ConfigManager {
    private static PYml config = new PYml("plugins/FutureTech/config.yml", false);

    public static String color() {
        return config.getString("color");
    }

    public static void reload() {
        config.reloadYaml();
        config = new PYml("plugins/FutureTech/config.yml", false);
        FutureTech.node = new PLangNode(FutureTech.instance(), ConfigManager.language());
    }

    public static String language() {
        return config.getString("language");
    }

    public static boolean enableResearch() {
        return config.getBoolean("research");
    }

    public static String itemGroup(String nodeID) {
        return FutureTech.node.lang("itemgroup." + nodeID);
    }

    public static String itemName(String itemID) {
        return FutureTech.node.lang("items." + itemID + ".name");
    }

    public static String[] itemLore(String itemID) {
        String[] lore = FutureTech.node.lang("items." + itemID + ".lore").split("\\|");
        return lore;
    }

    public static String blockName(String itemID) {
        return FutureTech.node.lang("blocks." + itemID + ".name");
    }

    public static String[] blockLore(String itemID) {
        String[] lore = FutureTech.node.lang("blocks." + itemID + ".lore").split("\\|");
        return lore;
    }

    public static String mobName(String itemID) {
        return FutureTech.node.lang("mobs." + itemID + ".name");
    }
}
