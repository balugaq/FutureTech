package net.bxx2004.pandalib.bukkit.gui.abandon;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 自定义翻页GUI
 */
@Deprecated
public class PPageGui extends CustomGui {
    private Inventory pagegui;
    private PItemStack tip;
    private PItemStack barrier;
    private PItemStack back;
    private PItemStack next;
    private String title;
    private int page;
    private List<PItemStack> items;
    private HashMap<Integer, List<PItemStack>> pageitem;
    private PListener pListener = new PListener() {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            int[] slot = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 47, 48, 49, 50, 51, 52, 53};
            List list = new ArrayList();
            for (int y : slot) {
                list.add(y);
            }
            if (event.getView().getTitle().equals(title) && event.getInventory().getSize() == 54) {
                if (list.contains(event.getRawSlot())) {
                    event.setCancelled(true);
                    Player player = (Player) event.getWhoClicked();
                    if (event.getRawSlot() == 46) {
                        if (page == 1) {
                            player.closeInventory();
                            player.sendMessage("没有上一页了...");
                        } else {
                            page = page - 1;
                            setTip(tip);
                            updateitems();
                        }
                    }
                    if (event.getRawSlot() == 52) {
                        if (page >= getAllPage()) {
                            player.closeInventory();
                            player.sendMessage("没有下一页了...");
                            page = 1;
                        } else {
                            page = page + 1;
                            setTip(tip);
                            updateitems();
                        }
                    }
                }
            }
        }

        @EventHandler
        public void onclose(InventoryCloseEvent event) {
            if (event.getView().getTitle().equals(title)) {
                unhook();
            }
        }
    }.hook(PandaLib.initPlugin.getName());
    private int speed;
    private Animation animation;

    /**
     * 构造一个带翻页的GUI
     *
     * @param title   GUI标题
     * @param tip     顶部提示物品
     * @param barrier 边框材质
     * @param back    上一页材质
     * @param next    下一页材质
     */
    public PPageGui(String title, PItemStack tip, PItemStack barrier, PItemStack back, PItemStack next) {
        this.pageitem = new HashMap<Integer, List<PItemStack>>();
        this.items = new ArrayList<PItemStack>();
        this.title = title;
        this.tip = tip;
        this.barrier = barrier;
        this.back = back;
        this.next = next;
        this.page = 1;
        this.pagegui = Bukkit.createInventory(null, 54, title.replaceAll("&", "§"));
        this.addListener(pListener);
        update();
    }

    /**
     * 为这个GUI添加物品
     *
     * @param customItems 添加的物品
     */
    public void addItem(PItemStack... customItems) {
        items.addAll(Arrays.asList(customItems));
    }

    /**
     * 为这个GUI移除一个物品
     *
     * @param item 移除的物品
     */
    public void removeItem(PItemStack item) {
        items.remove(item);
    }

    /**
     * 获取这个GUI的全部物品
     *
     * @return 获取GUI里面的全部物品
     */
    public List<PItemStack> getItems() {
        return items;
    }

    /**
     * 移除一个页面的物品
     *
     * @param page 页面序号
     */
    public void removePageItem(int page) {
        pageitem.remove(page);
        updateitems();
    }

    /**
     * 移除所有翻页GUI添加的物品
     */
    public void clear() {
        items.clear();
    }

    private void update() {
        pagegui.clear();
        int[] ints = new int[]{0, 1, 2, 3, 5, 6, 7, 8, 45, 47, 48, 49, 50, 51, 53};
        for (int i : ints) {
            this.pagegui.setItem(i, this.barrier);
        }
        this.pagegui.setItem(4, tip);
        this.pagegui.setItem(46, back);
        this.pagegui.setItem(52, next);
    }

    private void updateitems() {
        pagegui.clear();
        update();
        int o = 0;
        for (int i = 1; i < (getAllPage() + 1); i++) {
            if ((o + 36) <= items.size()) {
                pageitem.put(i, items.subList(o, (o + 36)));
            } else {
                pageitem.put(i, items.subList(o, (o + (items.size() - o))));
            }
            o = o + 36;
        }
        int[] ints1 = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        int b = 0;
        for (int a : ints1) {
            try {
                this.pagegui.setItem(a, pageitem.get(page).get(b));
            } catch (Exception e) {
            }
            b = b + 1;
        }
    }

    /**
     * @param page 页面
     * @return 给定页面的所有物品
     */
    public List<PItemStack> getItemOfPage(int page) {
        return pageitem.get(page);
    }

    /**
     * 获取当前GUI共多少页
     *
     * @return 当前GUI共几页
     */
    public int getAllPage() {
        if ((items.size() % 36) == 0) {
            return (items.size() / 36);
        } else {
            return (items.size() / 36) + 1;
        }
    }

    /**
     * 获取当前页
     *
     * @return 当前页面
     */
    public int getPage() {
        return this.page;
    }

    /**
     * 设置当前页面
     *
     * @param i 页
     */
    public void setPage(int i) {
        this.page = i;
    }

    /**
     * @return 返回GUI标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 设置GUI标题
     */
    public void setTitle(String title) {
        this.title = title;
        update();
    }

    /**
     * @return 返回顶部提示物品
     */
    public PItemStack getTip() {
        return tip;
    }

    /**
     * @param tip 设置顶部提示物品
     */
    public void setTip(PItemStack tip) {
        this.tip = tip;
        update();
    }

    /**
     * @return 返回边框材质
     */
    public PItemStack getBarrier() {
        return barrier;
    }

    /**
     * @param barrier 设置边框材质
     */
    public void setBarrier(PItemStack barrier) {
        this.barrier = barrier;
        update();
    }

    /**
     * @return 返回上一页材质
     */
    public PItemStack getBack() {
        return back;
    }

    /**
     * @param back 设置上一页材质
     */
    public void setBack(PItemStack back) {
        this.back = back;
        update();
    }

    /**
     * @return 返回下一页材质
     */
    public PItemStack getNext() {
        return next;
    }

    /**
     * @param next 设置下一页材质
     */
    public void setNext(PItemStack next) {
        this.next = next;
        update();
    }

    @Override
    public CustomGuiType getCustomGuiType() {
        return CustomGuiType.PAGE_GUI;
    }

    @Override
    public Inventory getInventory() {
        return pagegui;
    }

    @Override
    public void openGui(Player player) {
        this.page = 1;
        if (animation == null) {
            player.openInventory(pagegui);
            if (items == null) {

            } else {
                updateitems();
            }
        } else {
            openAnimation(player, this.animation, speed);
        }
    }

    @Override
    public void addListener(PListener event) {

    }


    @Override
    public void setAnimation(Animation animation, int speed) {
        this.animation = animation;
        this.speed = speed;
    }

    private void openAnimation(Player player, Animation animation, int speed) {
        ItemStack[] backs = pagegui.getContents();
        this.pagegui.clear();
        player.openInventory(pagegui);
        if (animation == Animation.BAR) {
            new BukkitRunnable() {
                int i = 18;

                @Override
                public void run() {
                    pagegui.setItem(i, new PItemStack(Material.BARRIER, "&e加载中···"));
                    i = i + 1;
                    if (i > 26) {
                        update();
                        if (items == null) {

                        } else {
                            updateitems();
                        }
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
        if (animation == Animation.BRUSH) {
            pagegui.setItem(9, new PItemStack(Material.BARRIER, "&e加载中···"));
            pagegui.setItem(27, new PItemStack(Material.BARRIER, "&e加载中···"));
            pagegui.setItem(45, new PItemStack(Material.BARRIER, "&e加载中···"));
            new BukkitRunnable() {
                int s1 = 0;
                int s2 = 10;
                int s3 = 18;
                int s4 = 28;
                int s5 = 36;
                int s6 = 46;

                @Override
                public void run() {
                    try {
                        pagegui.setItem(s1, new PItemStack(Material.BARRIER, "&e加载中···"));
                        pagegui.setItem(s2, new PItemStack(Material.BARRIER, "&e加载中···"));
                        pagegui.setItem(s3, new PItemStack(Material.BARRIER, "&e加载中···"));
                        pagegui.setItem(s4, new PItemStack(Material.BARRIER, "&e加载中···"));
                        pagegui.setItem(s5, new PItemStack(Material.BARRIER, "&e加载中···"));
                        pagegui.setItem(s6, new PItemStack(Material.BARRIER, "&e加载中···"));
                        s1 = s1 + 1;
                        s2 = s2 + 1;
                        s3 = s3 + 1;
                        s4 = s4 + 1;
                        s5 = s5 + 1;
                        s6 = s6 + 1;
                    } catch (Exception e) {
                        update();
                        if (items == null) {

                        } else {
                            updateitems();
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
                        pagegui.setItem(s3, new PItemStack(Material.AIR));
                        pagegui.setItem(s1, new PItemStack(Material.BARRIER, "&e加载中···"));
                    }
                    if (who == 1 || who == 4 || who == 7) {
                        pagegui.setItem(s1, new PItemStack(Material.AIR));
                        pagegui.setItem(s2, new PItemStack(Material.BARRIER, "&e加载中···"));
                    }
                    if (who == 2 || who == 5 || who == 8) {
                        pagegui.setItem(s2, new PItemStack(Material.AIR));
                        pagegui.setItem(s3, new PItemStack(Material.BARRIER, "&e加载中···"));
                    }
                    who = who + 1;
                    if (who >= 9) {
                        update();
                        if (items == null) {

                        } else {
                            updateitems();
                        }
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, speed);
        }
    }
}
