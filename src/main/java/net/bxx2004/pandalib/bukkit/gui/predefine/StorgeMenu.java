package net.bxx2004.pandalib.bukkit.gui.predefine;

import net.bxx2004.pandalib.bukkit.gui.HolderFactory;
import net.bxx2004.pandalib.bukkit.gui.PMenu;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class StorgeMenu extends PMenu {
    public static HashMap<String, StorgeMenu> datas = new HashMap<String, StorgeMenu>();
    private Inventory inventory;

    public StorgeMenu(String title, int size) {
        super();
        this.inventory = Bukkit.createInventory(holder(), size, title);
    }

    public static StorgeMenu data(String player) {
        if (datas.containsKey(player)) {
            return datas.get(player);
        } else {
            return null;
        }
    }

    @Override
    public void open(Player player) {
        super.open(player);
        datas.put(player.getName(), this);
    }

    @Override
    public InventoryHolder holder() {
        return HolderFactory.register(this.toString(), true);
    }

    @Override
    public void close(InventoryCloseEvent event) {

    }

    @Override
    public void click(InventoryClickEvent event) {

    }

    @Override
    public void open(InventoryOpenEvent event) {

    }

    @Override
    public String[] notClickString() {
        return new String[0];
    }

    @Override
    public int[] notClickSlot() {
        return new int[0];
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        return new HashMap<Integer, PItemStack>();
    }
}
