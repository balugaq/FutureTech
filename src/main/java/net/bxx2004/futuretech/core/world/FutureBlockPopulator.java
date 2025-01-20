package net.bxx2004.futuretech.core.world;

import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.pandalib.bukkit.entity.PEntity;
import net.bxx2004.pandalib.bukkit.entity.PEntityMeta;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Random;

public class FutureBlockPopulator extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(16) < 1) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = 255;
            while (chunk.getBlock(x, y, z).getType() == Material.AIR) y--;
            chunk.getBlock(x, y, z).setType(Material.IRON_ORE);
        }
        if (random.nextInt(128) < 1) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = 255;
            PEntity entity = new PEntity(chunk.getBlock(x, y + 1, z).getLocation(), EntityType.PHANTOM);
            PEntityMeta meta = entity.getMeta();
            meta.setDisplayName(ConfigManager.mobName("FT_BOOS"));
            entity.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(281);
            entity.getEntity().getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(30);
            entity.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
            entity.getEntity().setHealth(280);
            entity.setMeta(meta);
            entity.getEntity().setMetadata("FutureTech", new FixedMetadataValue(FutureTech.instance(), "FT_METEOR"));
        }
        if (random.nextInt(6) < 1) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = 255;
            while (chunk.getBlock(x, y, z).getType() == Material.AIR) y--;
            chunk.getBlock(x, y, z).setType(Material.CRYING_OBSIDIAN);
            PEntity entity = new PEntity(chunk.getBlock(x, y + 1, z).getLocation(), EntityType.VINDICATOR);
            PEntityMeta meta = entity.getMeta();
            meta.setDisplayName(ConfigManager.mobName("FT_SIRI"));
            entity.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(31);
            entity.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7);
            entity.getEntity().setHealth(30);
            entity.setMeta(meta);
            entity.getEntity().setMetadata("FutureTech", new FixedMetadataValue(FutureTech.instance(), "FT_SIRI"));
        }
        if (random.nextInt(64) < 1) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = 255;
            while (chunk.getBlock(x, y, z).getType() == Material.AIR) y--;
            for (int i = 0; i < 5; i++) {
                chunk.getBlock(x, y, z).getLocation().clone().add(i, i, i).getBlock().setType(Material.GLOWSTONE);
            }
            PEntity entity = new PEntity(chunk.getBlock(x, y + 1, z).getLocation(), EntityType.IRON_GOLEM);
            PEntityMeta meta = entity.getMeta();
            meta.setDisplayName(ConfigManager.mobName("FT_METEOR"));
            entity.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(101);
            entity.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
            entity.getEntity().setHealth(80);
            entity.setMeta(meta);
            entity.getEntity().setMetadata("FutureTech", new FixedMetadataValue(FutureTech.instance(), "FT_METEOR"));
            for (int i = 0; i < 5; i++) {
                chunk.getBlock(x, y + 10, z).getLocation().clone().add(i, -i, i).getBlock().setType(Material.GLOWSTONE);
            }
        }
    }
}
