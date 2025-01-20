package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_ENERGYBLOCK extends Item<PItemStack> {
    public FT_ENERGYBLOCK(){
        super();
    }
    @Override
    public PItemStack itemStack() {
        return new PItemStack(
                Material.AMETHYST_BLOCK,
                ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID())
        );
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        return RecipeType.COMPRESSOR;
    }

    @Override
    public ItemStack[] recipe() {
        PItemStack itemStack = new PItemStack(new FT_ENERGYORE().getItem().getItem());
        itemStack.setAmount(9);
        return new ItemStack[]{itemStack,null,null,null,null,null,null,null,null};
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
