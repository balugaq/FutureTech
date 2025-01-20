package net.bxx2004.pandalib.bukkit.gui.abandon;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 自定义一个简单带边框的GUI
 */
@Deprecated
public class PSimpleGui extends CustomGui {
    private String title;
    private PItemStack barrier;
    private Inventory simplegui;
    private Animation animation;
    private int speed;

    /**
     * 构造一个类似于Slimefun的合成表GUI
     *
     * @param title   GUI标题
     * @param barrier 边框材质
     */
    public PSimpleGui(String title, PItemStack barrier) {
        this.title = title;
        this.barrier = barrier;
        this.simplegui = Bukkit.createInventory(null, 45, title.replaceAll("&", "§"));
        update();
    }

    private void update() {
        int[] slot = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        for (int i = 0; i < 18; i++) {
            this.simplegui.setItem(slot[i], this.barrier);
        }
    }

    @Override
    public void openGui(Player player) {
        if (animation == null) {
            player.openInventory(simplegui);
        } else {
            openAnimation(player, this.animation, speed);
        }
    }

    @Override
    public CustomGuiType getCustomGuiType() {
        return CustomGuiType.SIMPLE_GUI;
    }

    @Override
    public Inventory getInventory() {
        return simplegui;
    }

    @Override
    public void addListener(PListener event) {

    }

    /**
     * 返回GUI标题
     *
     * @return 返回GUI标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置GUI标题
     *
     * @param title GUI标题
     */
    public void setTitle(String title) {
        this.title = title;
        update();
    }

    /**
     * 返回边框材质
     *
     * @return 返回边框材质
     */
    public PItemStack getBarrier() {
        return this.barrier;
    }

    /**
     * 设置边框材质
     *
     * @param barrier 边框材质
     */
    public void setBarrier(PItemStack barrier) {
        this.barrier = barrier;
        update();
    }

    @Override
    public void setAnimation(Animation animation, int speed) {
        this.animation = animation;
        this.speed = speed;
    }

    private void openAnimation(Player player, Animation animation, int speed) {
        ItemStack[] backs = simplegui.getContents();
        this.simplegui.clear();
        player.openInventory(simplegui);
        if (animation == Animation.BAR) {
            new BukkitRunnable() {
                int i = 18;

                @Override
                public void run() {
                    simplegui.setItem(i, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                    i = i + 1;
                    if (i > 26) {
                        simplegui.clear();
                        for (int o = 0; o < backs.length; o++) {
                            simplegui.setItem(o, backs[o]);
                        }
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
        if (animation == Animation.BRUSH) {
            simplegui.setItem(0, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            simplegui.setItem(1, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            simplegui.setItem(2, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            simplegui.setItem(9, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            simplegui.setItem(10, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            simplegui.setItem(18, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
            new BukkitRunnable() {
                int s1 = 3;
                int s2 = 11;
                int s3 = 19;
                int s4 = 27;
                int s5 = 36;

                @Override
                public void run() {
                    try {
                        simplegui.setItem(s1, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                        simplegui.setItem(s2, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                        simplegui.setItem(s3, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                        simplegui.setItem(s4, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                        simplegui.setItem(s5, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                        s1 = s1 + 1;
                        s2 = s2 + 1;
                        s3 = s3 + 1;
                        s4 = s4 + 1;
                        s5 = s5 + 1;
                    } catch (Exception e) {
                        simplegui.clear();
                        for (int o = 0; o < backs.length; o++) {
                            simplegui.setItem(o, backs[o]);
                        }
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
        if (animation == Animation.LOAD) {
            new BukkitRunnable() {
                int who = 0;
                int s1 = 21;
                int s2 = 22;
                int s3 = 23;

                @Override
                public void run() {
                    if (who == 0 || who == 3 || who == 6) {
                        simplegui.setItem(s3, new PItemStack(Material.AIR));
                        simplegui.setItem(s1, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                    }
                    if (who == 1 || who == 4 || who == 7) {
                        simplegui.setItem(s1, new PItemStack(Material.AIR));
                        simplegui.setItem(s2, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                    }
                    if (who == 2 || who == 5 || who == 8) {
                        simplegui.setItem(s2, new PItemStack(Material.AIR));
                        simplegui.setItem(s3, new PItemStack(Material.WHITE_STAINED_GLASS_PANE, "&e加载中···"));
                    }
                    who = who + 1;
                    if (who >= 9) {
                        simplegui.clear();
                        for (int o = 0; o < backs.length; o++) {
                            simplegui.setItem(o, backs[o]);
                        }
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
    }
}
