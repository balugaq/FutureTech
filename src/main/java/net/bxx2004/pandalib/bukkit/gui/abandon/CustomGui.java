package net.bxx2004.pandalib.bukkit.gui.abandon;

import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * 快速自定义GUI的抽象类
 */
@Deprecated
public abstract class CustomGui {
    /**
     * 创建一个非常非常普通的GUI
     *
     * @param title 标题
     * @param size  大小
     * @return CustomGUI
     */
    public static CustomGui createGUI(String title, int size) {
        return new CustomGui() {
            private Inventory gui = Bukkit.createInventory(null, size, title.replaceAll("&", "§"));

            @Override
            public CustomGuiType getCustomGuiType() {
                return CustomGuiType.CUSTOM_GUI;
            }

            @Override
            public Inventory getInventory() {
                return gui;
            }

            @Override
            public void openGui(Player player) {
                player.openInventory(getInventory());
            }

            @Override
            public void setAnimation(Animation animation, int speed) {

            }

            @Override
            public void addListener(PListener pListener) {

            }
        };
    }

    /**
     * 得到CustomGui的类型
     *
     * @return 得到一个CustomGui的类型
     */
    public abstract CustomGuiType getCustomGuiType();

    /**
     * 转换成一个Inventory
     *
     * @return Inventory类
     */
    public abstract Inventory getInventory();

    /**
     * 打开这个GUI
     *
     * @param player 玩家
     */
    public abstract void openGui(Player player);

    /**
     * 为这个GUI设置动画
     *
     * @param animation 动画类型
     * @param speed     速度
     */
    public abstract void setAnimation(Animation animation, int speed);

    /**
     * 为这个GUI添加一个监听器
     *
     * @param pListener 一个PListener监听器抽象类
     */
    public abstract void addListener(PListener pListener);
}
