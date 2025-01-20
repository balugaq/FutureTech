package net.bxx2004.futuretech.slimefun.inventory;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.bxx2004.futuretech.core.utils.RegisterMenu;
import net.bxx2004.futuretech.slimefun.Tools;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@RegisterMenu
public class CraftMenu extends Menu {
    private static Inventory inventory = Bukkit.createInventory(Tools.setHolder("CraftMenu"), 54, "未来科技制造台");

    public CraftMenu() {
        super();
    }

    public static void addRecipe(PItemStack[] itemStacks, SlimefunItem results) {
        GuideMenu.recipe.add(itemStacks);
        GuideMenu.result.add(results);
    }

    private static PItemStack[] nullTranslate(PItemStack[] test) {
        PItemStack[] res = new PItemStack[25];
        for (int i = 0; i < test.length; i++) {
            if (test[i] == null) {
                res[i] = new PItemStack(Material.BARRIER, "空");
            } else {
                res[i] = test[i];
            }
        }
        return res;
    }

    private static boolean canWork(PItemStack[] one, PItemStack[] two) {
        for (int i = 0; i < one.length; i++) {
            if (one[i].To().equals(two[i].To())) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public int[] canUseSlots() {
        return new int[]{9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 25, 27, 28, 29, 30, 31, 36, 37, 38, 39, 40, 43, 45, 46, 47, 48, 49};
    }

    @Override
    public void click(Player player, PItemStack itemStack, int slot) {
        if (slot == 16) {
            int[] c = new int[]{9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49};
            PItemStack[] recipe = new PItemStack[25];
            for (int i = 0; i < c.length; i++) {
                if (inventory.getItem(c[i]) == null) {
                    recipe[i] = new PItemStack(Material.BARRIER, "空");
                } else {
                    recipe[i] = new PItemStack(inventory.getItem(c[i]));
                }
            }
            int o = 0;
            for (PItemStack[] cacheRepice : GuideMenu.recipe) {
                if (canWork(nullTranslate(recipe), nullTranslate(cacheRepice))) {
                    if (SlimefunItem.getByItem(inventory.getItem(43)) != null) {
                        if (SlimefunItem.getByItem(inventory.getItem(43)).getId().equals("BATTERY")) {
                            for (int i : canUseSlots()) {
                                inventory.setItem(i, new ItemStack(Material.AIR));
                            }
                            inventory.setItem(25, GuideMenu.result.get(o).getItem());
                        }
                    }
                }
                o = o + 1;
            }
        }
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        HashMap<Integer, PItemStack> map = new HashMap<>();
        int[] barrier = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 15, 14, 16, 17, 23, 24, 26, 33, 32, 34, 35, 41, 42, 44, 50, 51, 53};
        for (int a : barrier) {
            map.put(a, new PItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        map.put(16, new PItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a点击这里制造"));
        map.put(52, new PItemStack(Material.GREEN_STAINED_GLASS_PANE, "&c请在上方放入电池"));
        return map;
    }
}
