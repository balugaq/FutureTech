package net.bxx2004.futuretech.slimefun.main.items.materials.cpu;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_CPU extends Item<PItemStack> {
    public FT_CPU() {
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.WHITE_STAINED_GLASS_PANE,
                ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        return RecipeType.ENHANCED_CRAFTING_TABLE;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON,
                SlimefunItems.GILDED_IRON, null, SlimefunItems.GILDED_IRON,
                SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON
        };
    }

    @Override
    public PListener listener() {
        return null;
    }

    @Override
    public Research research() {
        return SlimefunFactory.CPU;
    }
}
