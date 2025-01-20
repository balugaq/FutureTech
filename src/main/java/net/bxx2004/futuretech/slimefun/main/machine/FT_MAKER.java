package net.bxx2004.futuretech.slimefun.main.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterBlock;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.Tools;
import net.bxx2004.futuretech.slimefun.inventory.CraftMenu;
import net.bxx2004.futuretech.slimefun.inventory.GuideMenu;
import net.bxx2004.futuretech.slimefun.main.Blocks;
import net.bxx2004.futuretech.slimefun.main.items.materials.basic.*;
import net.bxx2004.futuretech.slimefun.main.items.materials.cpu.FT_SIRICPU;
import net.bxx2004.futuretech.slimefun.main.items.object.FT_COPPERSWORD;
import net.bxx2004.futuretech.slimefun.main.items.robot.FT_SIRIROBOT;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@RegisterBlock
public class FT_MAKER extends Blocks {
    public FT_MAKER(){
        super();
    }
    @Override
    public void doSomeThing() {
        new BukkitRunnable(){
            @Override
            public void run() {
                GuideMenu.addRecipe(new PItemStack[]{
                        null,null,new PItemStack(new FT_SIRICPU().getItem().getItem()),null,null,
                        new PItemStack(new FT_ROBOTARM().getItem().getItem()),new PItemStack(new FT_ROBOTARM().getItem().getItem()),new PItemStack(SlimefunItems.ANDROID_MEMORY_CORE),new PItemStack(new FT_ROBOTARM().getItem().getItem()),new PItemStack(new FT_ROBOTARM().getItem().getItem()),
                        new PItemStack(new FT_ROBOTARM().getItem().getItem()),null,new PItemStack(SlimefunItems.POWER_CRYSTAL.getItem().getItem()),null,new PItemStack(new FT_ROBOTARM().getItem().getItem()),
                        null,new PItemStack(SlimefunItems.ELECTRIC_MOTOR),null,new PItemStack(SlimefunItems.ELECTRIC_MOTOR),null,
                        null,new PItemStack(SlimefunItems.ELECTRIC_MOTOR),null,new PItemStack(SlimefunItems.ELECTRIC_MOTOR),null

                }, new FT_SIRIROBOT().getItem());
                GuideMenu.addRecipe(new PItemStack[]{
                        new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),
                        new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(Material.CRYING_OBSIDIAN),
                        new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(Material.CRYING_OBSIDIAN),
                        new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE.getItem().getItem()),new PItemStack(Material.CRYING_OBSIDIAN),
                        new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),new PItemStack(Material.CRYING_OBSIDIAN),

                }, new FT_DOOR().getItem());
                GuideMenu.addRecipe(new PItemStack[]{
                        new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),
                        new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),
                        new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),
                        new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),
                        new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem()),new PItemStack(SlimefunItems.MAGICAL_GLASS.getItem().getItem())
                }, new FT_CORE().getItem());
                GuideMenu.addRecipe(new PItemStack[]{
                        null,null,null,null,new FT_METEORCOPPER().itemStack(),
                        null,null,null,new FT_METEORCOPPER().itemStack(),null,
                        new FT_METEORCOPPER().itemStack(),null,new FT_METEORCOPPER().itemStack(),null,null,
                        null,new FT_METEORCOPPER().itemStack(),null,null,null,
                        new FT_METEORCOPPER().itemStack(),null,new FT_METEORCOPPER().itemStack(),null,null,
                }, new FT_COPPERSWORD().getItem());
            }
        }.runTaskAsynchronously(FutureTech.instance());
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MACHINE;
    }

    @Override
    public SlimefunItemStack item() {
        return new SlimefunItemStack(getID(),new PItemStack(Material.BLAST_FURNACE,
                ConfigManager.blockName("FT_MAKER"),
                ConfigManager.blockLore("FT_MAKER")
        ));
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                new ItemStack(Material.TORCH),new ItemStack(Material.AMETHYST_CLUSTER),new ItemStack(Material.TORCH),
                new ItemStack(Material.COBBLESTONE_WALL),new ItemStack(Material.BLAST_FURNACE),new ItemStack(Material.COBBLESTONE_WALL),
                new ItemStack(Material.DIAMOND_BLOCK),new ItemStack(Material.SCAFFOLDING),new ItemStack(Material.DIAMOND_BLOCK)
        };
    }

    @Override
    public BlockFace face() {
        return BlockFace.SELF;
    }

    @Override
    public void onClick(Player player, Block block) {
        new CraftMenu().open(player);
    }
}
