package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.machine.FT_MAKER;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_DOOR extends Item<PItemStack> {
    public FT_DOOR() {
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.CRYING_OBSIDIAN, ConfigManager.itemName(getID()), ConfigManager.itemLore(getID()));
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
        return null;
    }

    @Override
    public Research research() {
        return SlimefunFactory.BASIC;
    }
}
