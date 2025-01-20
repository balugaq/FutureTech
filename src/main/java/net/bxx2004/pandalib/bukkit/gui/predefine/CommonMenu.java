package net.bxx2004.pandalib.bukkit.gui.predefine;

import net.bxx2004.pandalib.bukkit.gui.HolderFactory;
import net.bxx2004.pandalib.bukkit.gui.PMenu;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class CommonMenu extends PMenu {
    private int[] noti;
    private String[] nots;
    private Inventory inventory;
    private HashMap<Integer, PItemStack> maps;

    public CommonMenu(String title, int size) {
        super();
        this.maps = new HashMap<>();
        this.inventory = Bukkit.createInventory(holder(), size, title);
    }

    @Override
    public InventoryHolder holder() {
        return HolderFactory.register(this.toString(), true);
    }

    public void setNoti(int[] noti) {
        this.noti = noti;
    }

    public void setNots(String[] nots) {
        this.nots = nots;
    }

    public void setItem(int i, PItemStack stack) {
        maps.put(i, stack);
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
        return nots;
    }

    @Override
    public int[] notClickSlot() {
        return noti;
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        return maps;
    }
}
