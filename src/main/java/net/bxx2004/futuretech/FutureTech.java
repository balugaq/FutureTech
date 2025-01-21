package net.bxx2004.futuretech;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.clip.placeholderapi.PlaceholderAPI;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.world.FutureWorld;
import net.bxx2004.futuretech.core.world.FutureWorldGenerator;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.language.application.PLangNode;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import net.bxx2004.pandalib.bukkit.task.depend.Depend;
import net.bxx2004.pandalib.bukkit.task.depend.MultiPluginDependTask;
import net.bxx2004.pandalib.bukkit.util.PCooldown;
import net.bxx2004.pandalib.bukkit.util.PMath;
import net.bxx2004.pandalibloader.BukkitPlugin;
import net.bxx2004.pandalibloader.Path;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Path(pack = "net.bxx2004.futuretech.FutureTech")
public class FutureTech extends BukkitPlugin implements SlimefunAddon {
    public static PLangNode node;
    public static PCooldown cooldown;
    public static boolean enabledPlaceHolderAPI = false;
    private static FutureTech plugin;

    public static FutureTech instance() {
        return plugin;
    }

    public String getPackage() {
        return "net.bxx2004.futuretech";
    }

    @Override
    public void start() {
        plugin = this;
        saveDefaultConfig();
        cooldown = new PCooldown(this);
        cooldown.addKey("FT_SIRIROBOT");
        node = new PLangNode(this, ConfigManager.language());
        init();
        enabledPlaceHolderAPI = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
        if (enabledPlaceHolderAPI) {
            try {
                PlaceholderAPI.containsPlaceholders("%FutureTech_SIRIROBOT%");
            } catch (Throwable ignored) {
                enabledPlaceHolderAPI = false;
            }
        }
        getLogger().info("########################################");
        getLogger().info("                FutureTech              ");
        getLogger().info("       作者: bxx2004 | Have a good time  ");
        getLogger().info("########################################");
    }

    @Override
    public void end() {

    }

    @Override
    public void load() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if (worldName.equalsIgnoreCase("ft_World")) {
            return new FutureWorldGenerator();
        }
        return null;
    }

    public void init() {
        saveResource("scripts/FT_SIRIROBOT.yml", false);
        SlimefunFactory.init();
        if (Bukkit.getWorld("ft_world") == null) {
            new FutureWorld().createWorld();
            new MultiPluginDependTask() {
                @Depend(name = "Multiverse-Core", version = "all", asynchronous = false)
                public void mu() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv import ft_world normal");
                }

                @Depend(name = "MyWorlds", version = "all", asynchronous = false)
                public void my() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load ft_world");
                }
            };
        }
        new PListener() {
            @EventHandler
            public void onDeath(EntityDeathEvent event) {
                if (event.getEntity() instanceof Vindicator) {
                    LivingEntity entity = event.getEntity();
                    if (entity.getCustomName() != null) {
                        if (entity.getCustomName().equals(ConfigManager.mobName("FT_METEOR"))) {
                            if (entity.hasMetadata("FutureTech")) {
                                event.getDrops().add(SlimefunItems.GOLD_24K);
                                event.getDrops().add(new PItemStack(Material.DIAMOND, PMath.getRandomAsInt(1, 8)));
                                event.getDrops().add(new PItemStack(Material.EXPERIENCE_BOTTLE, PMath.getRandomAsInt(1, 8)));
                            }
                        }
                        if (entity.getCustomName().equals(ConfigManager.mobName("FT_BOOS"))) {
                            if (entity.hasMetadata("FutureTech")) {
                                event.getDrops().add(SlimefunItems.GOLD_24K);
                                event.getDrops().add(new PItemStack(Material.DIAMOND, PMath.getRandomAsInt(1, 8)));
                                event.getDrops().add(new PItemStack(Material.EXPERIENCE_BOTTLE, PMath.getRandomAsInt(1, 8)));
                            }
                        }
                    }
                }
            }
        }.hook("FutureTech");
    }

    @NotNull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    public String getBugTrackerURL() {
        return "null";
    }
}
