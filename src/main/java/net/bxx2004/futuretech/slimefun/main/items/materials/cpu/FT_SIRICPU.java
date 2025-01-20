package net.bxx2004.futuretech.slimefun.main.items.materials.cpu;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.machine.FT_CPUMAKER;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_SIRICPU extends Item<SlimefunItemStack> {
    public FT_SIRICPU() {
        super();
    }

    @Override
    public SlimefunItemStack itemStack() {
        return new SlimefunItemStack("FT_SIRICPU", "890b1cd0cb10dcc3e99bf4104b10360c9279fa0a2aa7bded1483359b0474e11e", ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        RecipeType t = new RecipeType(new FT_CPUMAKER().itemStack(), "FT_CPUMAKER");
        return t;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                null, null, null,
                new FT_CPU().getItem().getItem(), null, new FT_SIRIMODEL().getItem().getItem(),
                null, null, null
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
