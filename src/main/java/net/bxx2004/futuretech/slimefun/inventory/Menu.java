package net.bxx2004.futuretech.slimefun.inventory;

import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Menu {
    public static HashMap<Integer, String> hookList = new HashMap<>();

    public Menu() {
        if (hookList.get(this.inventory().hashCode()) == null) {
            new PListener() {
                @EventHandler
                public void clickEvent(InventoryClickEvent event) {
                    try {
                        if (event.getClickedInventory().getHolder().equals(inventory().getHolder())) {
                            if (canUseSlots().length <= 0) {
                                event.setCancelled(true);
                            } else {
                                List<Integer> list = new ArrayList<>();
                                for (int i : canUseSlots()) {
                                    list.add(i);
                                }
                                if (!list.contains(event.getRawSlot())) {
                                    event.setCancelled(true);
                                }
                            }
                            try {
                                if (event.getCurrentItem() == null) {
                                    click((Player) event.getWhoClicked(), null, event.getRawSlot());
                                } else {
                                    click((Player) event.getWhoClicked(), new PItemStack(event.getCurrentItem()), event.getRawSlot());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }.hook("FutureTech");
            hookList.put(this.inventory().hashCode(), "true");
        }
    }

    public abstract int[] canUseSlots();

    public abstract void click(Player player, PItemStack itemStack, int slot);

    public abstract Inventory inventory();

    public abstract HashMap<Integer, PItemStack> layout();

    public void open(Player player) {
        for (int a : layout().keySet()) {
            inventory().setItem(a, layout().get(a));
        }
        player.openInventory(inventory());
    }
}
