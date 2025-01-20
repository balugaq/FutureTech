package net.bxx2004.futuretech.core.world;

import net.bxx2004.pandalib.bukkit.putil.PMath;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// clone https://www.mcbbs.net/thread-811614-1-1.html
public class FutureWorldGenerator extends ChunkGenerator {

    private SimplexOctaveGenerator noise;

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        List<BlockPopulator> list = new ArrayList<>();
        list.add(new FutureBlockPopulator());
        list.add(new FutureOrePopulator());
        return list;
    }
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        if (noise == null) {
            noise = new SimplexOctaveGenerator(world.getSeed(), 1);
            noise.setScale(0.005D);
        }
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int realX = chunkX * 16 + x;
                int realZ = chunkZ * 16 + z;
                double noiseValue = noise.noise(realX, realZ, 0.5D, 0.5D);
                int height = (int) (noiseValue * 40D + 100D);
                for (int y = 0; y < height - 1; y++) {
                    if (PMath.getRandomAsInt(0,10) > 7){
                        chunkData.setBlock(x, y, z, Material.MAGMA_BLOCK);
                    }else {
                        chunkData.setBlock(x, y, z, Material.SOUL_SOIL);
                    }
                }
                chunkData.setBlock(x, 0, z, Material.BEDROCK);
                chunkData.setBlock(x, height - 1, z, Material.DIRT);
                chunkData.setBlock(x, height, z, Material.PODZOL);
            }
        }
        return chunkData;
    }
}
