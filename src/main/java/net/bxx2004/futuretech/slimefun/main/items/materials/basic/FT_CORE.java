package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.machine.FT_MAKER;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_CORE extends Item<PItemStack> {
    public FT_CORE() {
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.BLACK_STAINED_GLASS_PANE, ConfigManager.itemName(getID()), ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        RecipeType t = new RecipeType(new FT_MAKER().item().clone(), "FT_MAKER");
        return t;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                null, null, null,
                null, new PItemStack(Material.BOOK, "&e在指导书内查看配方"), null,
                null, null, null
        };
    }

    @Override
    public PListener listener() {
        return new PListener() {
            @EventHandler
            public void onThrow(PlayerItemConsumeEvent event) {
                if (event.getItem().getType().equals(Material.MILK_BUCKET)) {
                    int radius = 5;
                    int doorSize = 0;
                    boolean core = false;
                    for (int x = -radius; x <= radius; ++x) {
                        for (int y = -radius; y <= radius; ++y) {
                            for (int z = -radius; z <= radius; ++z) {
                                Block blockAt = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().clone().add((double) x, (double) y, (double) z));
                                if (BlockStorage.hasBlockInfo(blockAt)) {
                                    if (BlockStorage.check(blockAt).getId().equalsIgnoreCase("FT_DOOR")) {
                                        doorSize = doorSize + 1;
                                    }
                                    if (BlockStorage.check(blockAt).getId().equalsIgnoreCase("FT_CORE")) {
                                        core = true;
                                    }
                                }
                            }
                        }
                    }
                    if (doorSize >= 8 && core) {
                        event.getPlayer().teleport(new Location(Bukkit.getWorld("ft_world"), 1, 20, 1));
                    }
                }
            }
        };
    }

    @Override
    public Research research() {
        return SlimefunFactory.BASIC;
    }
}
