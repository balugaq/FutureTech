package net.bxx2004.futuretech.slimefun;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_COOPER;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_CORE;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_DOOR;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_ENERGYBLOCK;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_ENERGYORE;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_GUIDE;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_METEORCOPPER;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_ROBOTARM;
import net.bxx2004.futuretech.slimefun.main.items.materials.cpu.FT_CPU;
import net.bxx2004.futuretech.slimefun.main.items.materials.cpu.FT_SIRICPU;
import net.bxx2004.futuretech.slimefun.main.items.materials.cpu.FT_SIRIMODEL;
import net.bxx2004.futuretech.slimefun.main.items.object.FT_COPPERSWORD;
import net.bxx2004.futuretech.slimefun.main.items.object.FT_SEARCHER;
import net.bxx2004.futuretech.slimefun.main.items.robot.FT_SIRIROBOT;
import net.bxx2004.futuretech.slimefun.main.machine.FT_CPUMAKER;
import net.bxx2004.futuretech.slimefun.main.machine.FT_MAKER;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.language.abandon.PAction;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SlimefunFactory {
    //分类注册
    public static final NestedItemGroup MAIN = new NestedItemGroup(
            Tools.key("main"),
            new PItemStack(Material.BLUE_STAINED_GLASS_PANE,
                    ConfigManager.color() + ConfigManager.itemGroup("main")).clone()
    );
    public static final SubItemGroup MACHINE = new SubItemGroup(
            Tools.key("machine"),
            MAIN,
            new PItemStack(Material.BEACON,
                    ConfigManager.color() + ConfigManager.itemGroup("machine")).clone()
    );
    public static final SubItemGroup ROBOT = new SubItemGroup(
            Tools.key("robot"),
            MAIN,
            new PItemStack("robot",
                    ConfigManager.color() + ConfigManager.itemGroup("robot")).clone()
    );
    public static final SubItemGroup MATERIALS = new SubItemGroup(
            Tools.key("materials"),
            MAIN,
            new PItemStack(Material.IRON_INGOT,
                    ConfigManager.color() + ConfigManager.itemGroup("materials")).clone()
    );
    public static final SubItemGroup OBJECT = new SubItemGroup(
            Tools.key("object"),
            MAIN,
            new PItemStack(Material.DIAMOND_BLOCK,
                    ConfigManager.color() + ConfigManager.itemGroup("object")).clone()
    );
    public static Research CPU;
    public static Research BASIC;
    public static Research ROBOT_R;
    public static Research OBJECT_R;

    public static void init() {
        MAIN.register(FutureTech.instance());
        ROBOT.register(FutureTech.instance());
        MACHINE.register(FutureTech.instance());
        OBJECT.register(FutureTech.instance());
        MATERIALS.register(FutureTech.instance());
        if (ConfigManager.enableResearch()) {
            CPU = new Research(Tools.key("CPU"), 5577881, "CPU", 10);
            BASIC = new Research(Tools.key("BASIC"), 5577882, "BASIC", 10);
            ROBOT_R = new Research(Tools.key("ROBOT"), 5577883, "ROBOT", 10);
            OBJECT_R = new Research(Tools.key("OBJECT"), 5577884, "OBJECT", 10);
        }

        new FT_COOPER();
        new FT_CORE();
        new FT_DOOR();
        new FT_ENERGYBLOCK();
        new FT_ENERGYORE();
        new FT_GUIDE();
        new FT_METEORCOPPER();
        new FT_ROBOTARM();
        new FT_CPU();
        new FT_SIRICPU();
        new FT_SIRIMODEL();
        new FT_COPPERSWORD();
        new FT_SEARCHER();
        new FT_SIRIROBOT();
        new FT_CPUMAKER();
        new FT_MAKER();

        if (ConfigManager.enableResearch()) {
            BASIC.register();
            OBJECT_R.register();
            CPU.register();
            ROBOT_R.register();
        }
        new PAction("cooldown") {
            @Override
            public Object run(Player player, String... strings) {
                String type = strings[0];
                if (type.equalsIgnoreCase("add")) {
                    float value = Float.parseFloat(strings[1]);
                    FutureTech.cooldown.addCoolDownOfKey("FT_SIRIROBOT", player, value);
                }
                if (type.equalsIgnoreCase("look")) {
                    return PMath.formatDouble(FutureTech.cooldown.lookCoolDownOfKey("FT_SIRIROBOT", player));
                }
                if (type.equalsIgnoreCase("is")) {
                    return FutureTech.cooldown.isCoolDown("FT_SIRIROBOT", player);
                }
                return null;
            }
        };
    }
}
