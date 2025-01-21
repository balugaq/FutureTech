package net.bxx2004.futuretech.core.world;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class FutureOrePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int x = random.nextInt(16);
        int y = random.nextInt(15) + 1;
        int z = random.nextInt(16);
        while (random.nextDouble() < 0.8D) {
            chunk.getBlock(x, y, z).setType(Material.DEEPSLATE_LAPIS_ORE);
            Location location = chunk.getBlock(x, y, z).getLocation();
            if (!StorageCacheUtils.hasBlock(location)) {
                Slimefun.getDatabaseManager().getBlockDataController().createBlock(location, "FT_ENERGYORE");
            }
        }
        if (random.nextInt(16) < 1) {
            int x1 = random.nextInt(16);
            int z1 = random.nextInt(16);
            int y1 = 255;
            while (chunk.getBlock(x1, y1, z1).getType() == Material.AIR) y1--;
            chunk.getBlock(x1, y1, z1).setType(Material.GILDED_BLACKSTONE);
            Location location = chunk.getBlock(x1, y1, z1).getLocation();
            if (!StorageCacheUtils.hasBlock(location)) {
                Slimefun.getDatabaseManager().getBlockDataController().createBlock(location, "FT_COOPER");
            }
        }
    }
}
