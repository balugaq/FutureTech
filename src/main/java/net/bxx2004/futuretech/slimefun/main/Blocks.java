package net.bxx2004.futuretech.slimefun.main;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import net.bxx2004.futuretech.FutureTech;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Blocks {
    private MultiBlockMachine machine;
    public Blocks() {
        if (SlimefunItem.getById(getID()) == null){
            MultiBlockMachine machine = new MultiBlockMachine(group(),item(),recipe(),face()){
                @Override
                public void onInteract(Player player, Block block) {
                    onClick(player,block);
                }
            };
            this.machine = machine;
            doSomeThing();
            machine.register(FutureTech.instance());
        }
    }
    public abstract void doSomeThing();
    public abstract ItemGroup group();
    public abstract SlimefunItemStack item();
    public abstract ItemStack[] recipe();
    public abstract BlockFace face();
    public abstract void onClick(Player player, Block block);
    public String getID(){
        return this.getClass().getSimpleName();
    }
    public MultiBlockMachine getMachine(){
        return this.machine;
    }
}
