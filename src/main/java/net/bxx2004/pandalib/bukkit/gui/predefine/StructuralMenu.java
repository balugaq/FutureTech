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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StructuralMenu extends PMenu {
    String[] index;
    private Inventory inventory;

    public StructuralMenu(String title, String... index) {
        super();
        this.index = index;
        inventory = Bukkit.createInventory(holder(), index.length * 9, title);
    }

    @Override
    public InventoryHolder holder() {
        return HolderFactory.register(this.toString(), true);
    }

    public void setItem(char v, PItemStack stack) {
        for (Integer integer : slot(v)) {
            inventory.setItem(integer, stack);
        }
    }

    public PItemStack getItem(char v) {
        return new PItemStack(inventory.getItem(slot(v).get(0)));
    }

    private List<Integer> slot(char v) {
        int i = 0;
        List<Integer> list = new ArrayList<>();
        for (String s : index) {
            char[] a = s.toCharArray();
            for (int b = 0; b < a.length; b++) {
                if (a[b] == v) {
                    list.add((i * 9) + b);
                }
            }
            i++;
        }
        return list;
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
        return new String[]{};
    }

    @Override
    public int[] notClickSlot() {
        return new int[]{};
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        return null;
    }
}
