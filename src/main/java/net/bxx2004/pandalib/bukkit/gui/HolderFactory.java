package net.bxx2004.pandalib.bukkit.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public interface HolderFactory {
    static HashMap<String, InventoryHolder> map = new HashMap<>();

    public static InventoryHolder register(String id, boolean def) {
        if (def) {
            if (map.get(id) == null) {
                map.put(id, new InventoryHolder() {
                    @Override
                    public Inventory getInventory() {
                        return null;
                    }
                });
                return map.get(id);
            }
        }
        return map.get(id);
    }

    public InventoryHolder holder();
}
