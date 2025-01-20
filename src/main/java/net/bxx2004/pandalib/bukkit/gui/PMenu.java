package net.bxx2004.pandalib.bukkit.gui;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public abstract class PMenu implements HolderFactory {
    public PMenu() {
        new PListener() {
            @EventHandler
            public void onClick(InventoryClickEvent event) {
                try {
                    if (event.getInventory().getHolder().equals(holder())) {
                        try {
                            for (int i : notClickSlot()) {
                                if (event.getRawSlot() == i) {
                                    event.setCancelled(true);
                                }
                            }
                            for (String s : notClickString()) {
                                if (event.getCurrentItem() != null) {
                                    try {
                                        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(s)) {
                                            event.setCancelled(true);
                                        }
                                        if (event.getCurrentItem().getItemMeta().getLore().contains(s)) {
                                            event.setCancelled(true);
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                        click(event);
                    }
                } catch (Exception e) {
                }
            }

            @EventHandler
            public void onOpen(InventoryOpenEvent event) {
                try {
                    if (event.getInventory().getHolder().equals(holder())) {
                        open(event);
                        if (layout() != null) {
                            layout().forEach((key, value) -> {
                                event.getInventory().setItem(key, value);
                            });
                        }
                    }
                } catch (Exception e) {
                }
            }

            @EventHandler
            public void onClose(InventoryCloseEvent event) {
                try {
                    if (event.getInventory().getHolder().equals(holder())) {
                        close(event);
                    }
                } catch (Exception e) {
                }
            }
        }.hook(PandaLib.initPlugin.getName());
    }

    public abstract void close(InventoryCloseEvent event);

    public abstract void open(InventoryOpenEvent event);

    public abstract void click(InventoryClickEvent event);

    public abstract String[] notClickString();

    public abstract int[] notClickSlot();

    public abstract Inventory inventory();

    public abstract HashMap<Integer, PItemStack> layout();

    public void open(Player player) {
        player.openInventory(inventory());
    }
}
