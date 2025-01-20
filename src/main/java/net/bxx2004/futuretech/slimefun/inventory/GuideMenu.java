package net.bxx2004.futuretech.slimefun.inventory;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterMenu;
import net.bxx2004.futuretech.slimefun.Tools;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RegisterMenu
public class GuideMenu extends Menu{
    private static Inventory inventory = Bukkit.createInventory(Tools.setHolder("GuideMenu"),54, ConfigManager.itemGroup("main"));
    public static List<PItemStack[]> recipe = new ArrayList<PItemStack[]>();
    public static List<SlimefunItem> result = new ArrayList<>();
    public static void addRecipe(PItemStack[] itemStacks,SlimefunItem results){
        recipe.add(itemStacks);
        result.add(results);
    }
    private static HashMap<String,Integer> pages = new HashMap<>();
    public GuideMenu(){
        super();
    }
    @Override
    public int[] canUseSlots() {
        return new int[0];
    }

    @Override
    public Inventory inventory() {
        return inventory;
    }

    @Override
    public void click(Player player, PItemStack itemStack, int slot) {
        if (pages.get(player.getName()) == null){
            pages.put(player.getName(),-1);
        }
        if (slot == 8){
            player.closeInventory();
            pages.put(player.getName(),0);
        }
        if (slot == 25){
            pages.put(player.getName(),pages.get(player.getName()) - 1);
            if (pages.get(player.getName()) < 0){
                player.closeInventory();
            }else {
                int[] lay = new int[]{9,10,11,12,13,18,19,20,21,22,27,28,29,30,31,36,37,38,39,40,45,46,47,48,49};
                int a = 0;
                for (int i : lay){
                    if (recipe.get(pages.get(player.getName()))[a] == null){
                        inventory().setItem(i,new ItemStack(Material.AIR));
                    }else {
                        inventory().setItem(i,recipe.get(pages.get(player.getName()))[a]);
                    }
                    a = a+1;
                }
                inventory().setItem(33,result.get(pages.get(player.getName())).getItem());
            }
        }
        if (slot == 43){
            pages.put(player.getName(),pages.get(player.getName()) + 1);
            if (pages.get(player.getName()) >= recipe.size()){
                player.closeInventory();
            }else {
                int[] lay = new int[]{9,10,11,12,13,18,19,20,21,22,27,28,29,30,31,36,37,38,39,40,45,46,47,48,49};
                int a = 0;
                for (int i : lay){
                    if (recipe.get(pages.get(player.getName()))[a] == null){
                        inventory().setItem(i,new ItemStack(Material.AIR));
                    }else {
                        inventory().setItem(i,recipe.get(pages.get(player.getName()))[a]);
                    }
                    a = a+1;
                }
                inventory().setItem(33,result.get(pages.get(player.getName())).getItem());
            }
        }
    }

    @Override
    public HashMap<Integer, PItemStack> layout() {
        HashMap<Integer,PItemStack> map = new HashMap<>();
        int[] barrier = new int[]{0,1,2,3,4,5,6,7,14,15,16,17,23,24,26,32,34,35,41,42,44,50,51,52,53};
        for (int a : barrier){
            map.put(a,new PItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        map.put(8,new PItemStack(Material.BARRIER,"&9Happy! bxx2004"));
        map.put(25,new PItemStack(Material.GREEN_STAINED_GLASS_PANE,"&a<---"));
        map.put(43,new PItemStack(Material.GREEN_STAINED_GLASS_PANE,"&e--->"));
        return map;
    }
}
