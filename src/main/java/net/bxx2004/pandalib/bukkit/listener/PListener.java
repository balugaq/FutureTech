package net.bxx2004.pandalib.bukkit.listener;

import net.bxx2004.pandalib.bukkit.util.PPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * 简单的监听器
 */
public class PListener implements Listener {
    private boolean hook = false;

    /**
     * 挂在一个PListener监听器
     *
     * @param pluginName 插件名称
     */
    public PListener hook(String pluginName) {
        if (!hook) {
            Bukkit.getServer().getPluginManager().registerEvents(this, PPlugin.getPlugin(pluginName));
            hook = true;
        }
        return this;
    }

    /**
     * 注销这个监听器
     */
    public void unhook() {
        HandlerList.unregisterAll(this);
    }
}
