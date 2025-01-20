package net.bxx2004.pandalib.bukkit.gui.predefine;

import net.bxx2004.pandalib.bukkit.gui.HolderFactory;
import net.bxx2004.pandalib.bukkit.gui.PMenu;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class FlipMenu extends PMenu {
    public int page;
    HashMap<Integer, PItemStack> button = new HashMap<>();
    private HashMap<Integer, PItemStack[]> map;
    private int[] use;
    private Inventory inventory;

    public FlipMenu(String title) {
        super();
        this.inventory = Bukkit.createInventory(holder(), 54, title);
        this.map = new HashMap<>();
        this.page = 0;
        this.use = new int[]{1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};
    }

    @Override
    public InventoryHolder holder() {
        return HolderFactory.register(this.toString(), true);
    }

    @Override
    public void close(InventoryCloseEvent event) {

    }

    @Override
    public void open(InventoryOpenEvent event) {

    }

    @Override
    public void click(InventoryClickEvent event) {
        if (event.getInventory().getHolder().equals(holder())) {
            if (event.getRawSlot() == 47) {
                if (page > 0) {
                    page = page - 1;
                    layout(page);
                }
            }
            if (event.getRawSlot() == 51) {
                if (page < (map.keySet().size() - 1)) {
                    page = page + 1;
                    layout(page);
                }
            }
        }
    }

    public void setItem(int index, PItemStack... itemStacks) {
        map.put(index, itemStacks);
    }

    public int page() {
        return page;
    }

    public void setButton(int i, PItemStack stack) {
        button.put(i, stack);
    }

    private void layout(int page) {
        inventory.clear();
        PItemStack[] item = map.get(page);
        int i = 0;
        for (int slot : use) {
            if (item.length <= i) {
                break;
            }
            if (item[i] == null) {
                continue;
            }
            inventory().setItem(slot, item[i]);
            i = i + 1;
        }
        inventory().setItem(47, new PItemStack(Material.ARROW, "&9\u21e6 上一页"));
        inventory().setItem(51, new PItemStack(Material.ARROW, "&9\u21e8 下一页"));
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        HashMap<Integer, PItemStack> re = new HashMap<>();
        int i = 0;
        for (int slot : use) {
            if (map.get(0).length <= i) {
                break;
            }
            re.put(slot, map.get(0)[i]);
            i = i + 1;
        }
        re.put(47, new PItemStack(Material.ARROW, "&9\u21e6 上一页"));
        re.put(51, new PItemStack(Material.ARROW, "&9\u21e8 下一页"));
        re.putAll(button);
        return re;
    }

    @Override
    public int[] notClickSlot() {
        return new int[]{0, 9, 18, 27, 36, 45, 46, 47, 48, 49, 50, 51, 52, 53, 8, 17, 26, 35, 44};
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public String[] notClickString() {
        return new String[0];
    }
}
