package net.bxx2004.futuretech.slimefun.main.items.object;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.Tools;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_COOPER;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.FT_METEORCOPPER;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@RegisterItem
public class FT_SEARCHER extends Item<PItemStack> implements Rechargeable {
    private float charge = 200;
    public FT_SEARCHER(){
        super();
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return charge;
    }

    @NotNull
    @Override
    public String getId() {
        return getID();
    }

    @Override
    public PItemStack itemStack() {
        PItemStack itemStack = new PItemStack(Material.SPYGLASS, ConfigManager.itemName(getID()),ConfigManager.itemLore(getID()));
        itemStack.setItemMeta(Tools.setCharge(itemStack.getItemMeta(),300,300));
        return itemStack;
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.OBJECT;
    }

    @Override
    public RecipeType type() {
        return RecipeType.ENHANCED_CRAFTING_TABLE;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{null,null,null,null, SlimefunItems.ELECTRO_MAGNET,null,null,new ItemStack(Material.DIAMOND_PICKAXE),null};
    }

    @Override
    public PListener listener() {
        return new PListener(){
            @EventHandler
            public void onBreak(PlayerInteractEvent event){
                try {
                    if (BlockStorage.hasBlockInfo(event.getClickedBlock().getLocation())){
                        if (BlockStorage.check(event.getClickedBlock(),new FT_COOPER().getID())){
                            event.setCancelled(true);
                            if (SlimefunItem.getByItem(event.getItem()) != null){
                                if (SlimefunItem.getByItem(event.getItem()).getId().equalsIgnoreCase("FT_SEARCHER")) {
                                    if (removeItemCharge(event.getItem(),1)){
                                        event.setCancelled(true);
                                        event.getClickedBlock().setType(Material.AIR);
                                        event.getClickedBlock().getWorld().dropItem(event.getClickedBlock().getLocation(),new FT_METEORCOPPER().getItem().getItem());
                                    }
                                }else {
                                    event.setCancelled(true);
                                }
                            }else {
                                event.setCancelled(true);
                            }
                        }
                    }
                }catch (Exception e){}
            }
        };
    }

    @Override
    public Research research() {
        return SlimefunFactory.OBJECT_R;
    }
}
