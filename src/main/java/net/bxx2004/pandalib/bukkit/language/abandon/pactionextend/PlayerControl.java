package net.bxx2004.pandalib.bukkit.language.abandon.pactionextend;

import net.bxx2004.pandalib.bukkit.language.abandon.PAction;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerControl {
    public PlayerControl() {
        //teleport world x y z | null
        new PAction("teleport") {
            @Override
            public Object run(Player player, String... args) {
                try {
                    player.teleport(new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                } catch (Exception e) {
                    Lang.error("Teleport error run " + player.getName() + " more...", PMath.toStringList(args));
                }
                return null;
            }
        };
    }
}
