package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.inventory.GuideMenu;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_GUIDE extends Item<PItemStack> {
    public FT_GUIDE() {
        super();
    }
    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.BOOK,
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
                null,null,null,
                null,new ItemStack(Material.WRITABLE_BOOK),null,
                null,null,null
        };
    }

    @Override
    public PListener listener() {
        return new PListener(){
            @EventHandler
            public void onClick(PlayerInteractEvent event){
                if (SlimefunItem.getByItem(event.getItem()) != null){
                    if (SlimefunItem.getByItem(event.getItem()).getId().equals("FT_GUIDE")){
                        new GuideMenu().open(event.getPlayer());
                    }
                }
            }
        };
    }

    @Override
    public Research research() {
        return null;
    }
}
