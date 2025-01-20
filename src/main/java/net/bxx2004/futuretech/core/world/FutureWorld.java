package net.bxx2004.futuretech.core.world;

import net.bxx2004.futuretech.slimefun.Tools;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

public class FutureWorld extends WorldCreator {
    public FutureWorld() {
        super("ft_world", Tools.key("ft_world"));
    }

    @Override
    public @Nullable ChunkGenerator generator() {
        return new FutureWorldGenerator();
    }
}
