package net.bxx2004.pandalib.bukkit.block;

import net.bxx2004.pandalib.java.util.MultipleReturn;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockIndex {
    private MultipleReturn<Integer> o;
    private Block oBlock;
    private World world;
    private String help = "Z 北减南加 X 西减东加 Y下减上加";

    public BlockIndex(String world, MultipleReturn o) {
        this.o = o;
        this.world = Bukkit.getWorld(world);
        init();
    }

    public BlockIndex(String world, int x, int y, int z) {
        this.o = new MultipleReturn<Integer>(x, y, z);
        this.world = Bukkit.getWorld(world);
        init();
    }

    private void init() {
        this.oBlock = world.getBlockAt(o.value(0), o.value(1), o.value(2));
    }

    public Block index(int x, int y, int z) {
        return world.getBlockAt(oBlock.getX() + x, oBlock.getY() + y, oBlock.getZ() + z);
    }
}
