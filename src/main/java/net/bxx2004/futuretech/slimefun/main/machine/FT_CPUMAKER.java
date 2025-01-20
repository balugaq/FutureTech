package net.bxx2004.futuretech.slimefun.main.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.Tools;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
@RegisterItem
public class FT_CPUMAKER extends Item<PItemStack>{
    private static Inventory inventory = Bukkit.createInventory(Tools.setHolder("FT_CPUMAKER"), 27,ConfigManager.blockName("FT_CPUMAKER"));
    public FT_CPUMAKER(){
        super();
    }

    @Override
    public PItemStack itemStack() {
        return new PItemStack(Material.BEACON,
                ConfigManager.blockName(getID()),
                ConfigManager.blockLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MACHINE;
    }

    @Override
    public RecipeType type() {
        return RecipeType.ENHANCED_CRAFTING_TABLE;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                SlimefunItems.STEEL_PLATE,SlimefunItems.STEEL_PLATE,SlimefunItems.STEEL_PLATE,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,SlimefunItems.ADVANCED_CIRCUIT_BOARD,SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.HEATING_COIL,SlimefunItems.ELECTRIC_MOTOR
        };
    }

    @Override
    public PListener listener() {
        return new PListener(){
            @EventHandler
            public void onClick(PlayerInteractEvent event){
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    Block block = event.getClickedBlock();
                    if (BlockStorage.check(block,"FT_CPUMAKER")){
                        event.setCancelled(true);
                        for (int i = 00 ;i<27;i++){
                            if (i == 12 || i==14 || i==22){
                                continue;
                            }
                            inventory.setItem(i,new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                        }
                        inventory.setItem(13,new PItemStack(Material.GREEN_STAINED_GLASS_PANE,"&e请点击这里制造").clone());
                        event.getPlayer().openInventory(inventory);
                    }
                }
            }
            @EventHandler
            public void onUse(InventoryClickEvent event){
                try {
                    if (event.getInventory().getHolder().equals(Tools.holder("FT_CPUMAKER"))){
                        int[] i = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,13,15,16,17,18,19,20,21,23,24,25,26};
                        for (int a : i){
                            if (event.getRawSlot() == a){
                                event.setCancelled(true);
                            }
                        }
                        SlimefunItem one = SlimefunItem.getByItem(event.getInventory().getItem(12));
                        SlimefunItem two = SlimefunItem.getByItem(event.getInventory().getItem(14));
                        if (event.getRawSlot() == 13){
                            if ((one != null) &&(two != null)){
                                if (one.getId().equals("FT_CPU")){
                                    switch (two.getId()){
                                        case "FT_SIRIMODEL":
                                            event.getInventory().setItem(12,new ItemStack(Material.AIR));
                                            event.getInventory().setItem(14,new ItemStack(Material.AIR));
                                            event.getInventory().setItem(22,SlimefunItem.getById("FT_SIRICPU").getItem());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e){

                }
            }
        };
    }

    @Override
    public Research research() {
        return SlimefunFactory.CPU;
    }
}
