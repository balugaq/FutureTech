package net.bxx2004.pandalib.bukkit.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class BoundingBox {
    private Block p1;
    private Block p2;

    public BoundingBox(Block p1, Block p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean isInAABB(Entity entity) {
        if (!entity.getWorld().getName().equals(p1.getWorld().getName())) {
            return false;
        }
        int x1 = p1.getX();
        int y1 = p1.getY();
        int z1 = p1.getZ();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int z2 = p2.getZ();
        int x = entity.getLocation().getBlockX();
        int y = entity.getLocation().getBlockY();
        int z = entity.getLocation().getBlockZ();
        if (x1 < x && x < x2) {
            if (y1 < y && y < y2) {
                if (z1 < z && z < z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInAABB(Block block) {
        if (!block.getWorld().getName().equals(p1.getWorld().getName())) {
            return false;
        }
        int x1 = p1.getX();
        int y1 = p1.getY();
        int z1 = p1.getZ();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int z2 = p2.getZ();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        if (x1 < x && x < x2) {
            if (y1 < y && y < y2) {
                if (z1 < z && z < z2) {
                    return true;
                }
            }
        }
        return false;
    }
}
