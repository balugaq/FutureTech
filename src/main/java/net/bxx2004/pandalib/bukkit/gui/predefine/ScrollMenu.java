package net.bxx2004.pandalib.bukkit.gui.predefine;

import net.bxx2004.pandalib.bukkit.gui.HolderFactory;
import net.bxx2004.pandalib.bukkit.gui.PMenu;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class ScrollMenu extends PMenu {
    public ScrollMenu() {
        super();
    }

    @Override
    public InventoryHolder holder() {
        return HolderFactory.register(this.toString(), false);
    }

    @Override
    public void close(InventoryCloseEvent event) {

    }

    @Override
    public void open(InventoryOpenEvent event) {

    }

    @Override
    public void click(InventoryClickEvent event) {

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
        return null;
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        return null;
    }
}
