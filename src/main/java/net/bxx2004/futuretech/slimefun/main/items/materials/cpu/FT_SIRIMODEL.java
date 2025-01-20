package net.bxx2004.futuretech.slimefun.main.items.materials.cpu;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_SIRIMODEL extends Item<PItemStack> {
    public FT_SIRIMODEL() {
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.BLUE_STAINED_GLASS_PANE,
                ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        return RecipeType.MOB_DROP;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                null, null, null,
                null, new PItemStack(Material.IRON_INGOT, "IRON_GOLEM"), null,
                null, null, null
        };
    }

    @Override
    public PListener listener() {
        return new PListener() {
            @EventHandler
            public void onSpawn(EntityDeathEvent event) {
                if (event.getEntityType() == EntityType.IRON_GOLEM) {
                    int i = PMath.getRandomAsInt(0, 10);
                    if (i >= 8) {
                        event.getDrops().add(SlimefunItem.getById("FT_SIRIMODEL").getItem());
                    }
                }
            }
        };
    }

    @Override
    public Research research() {
        return SlimefunFactory.CPU;
    }
}
