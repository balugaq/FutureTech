package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_COOPER extends Item<PItemStack> {
    public FT_COOPER() {
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(
                Material.GILDED_BLACKSTONE,
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
        return RecipeType.NULL;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{null, null, null, null, new PItemStack(Material.DEEPSLATE_LAPIS_ORE, "由未来世界产出"), null, null, null, null};
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
