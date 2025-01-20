package net.bxx2004.pandalib.bukkit.file;

import net.bxx2004.pandalib.bukkit.language.abandon.PAction;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class PPds {
    private PTxt txt;

    /**
     * 构造一个pda脚本文件
     *
     * @param path 文件路径
     */
    public PPds(String path) {
        if (path.endsWith(".pds")) {
            this.txt = new PTxt(path);
        } else {
            Lang.print(path + "&c不是一个有效的 .pds 文件...");
        }
    }

    /**
     * 获取文件类型
     *
     * @return 文件类型
     */
    public FileType getType() {
        return FileType.PDA;
    }

    /**
     * 输出一行动作
     *
     * @param word 动作
     */
    public void println(String word) {
        if (PAction.has(word)) {
            txt.println(word);
        } else {
            Lang.print("&c[&fPAction&c] &c无法输出,该动作 &f" + word + " &c不存在...");
        }
    }

    /**
     * 读取一行动作,不会执行
     *
     * @param i 第几行
     * @return 动作
     */
    public String read(int i) {
        return txt.read(i);
    }

    /**
     * 执行某一行动作
     *
     * @param i 某行
     */
    public void run(int i, Player player) {
        String a = txt.read(i);
        if (!a.trim().isEmpty()) {
            PAction.go(a, player);
        }
    }

    /**
     * 执行该文件所有动作
     */
    public void run(Player player) {
        Iterator i = iterator();
        while (i.hasNext()) {
            PAction.go((String) i.next(), player);
        }
    }

    /**
     * 遍历所有动作
     *
     * @return 所有动作
     */
    public Iterator iterator() {
        return txt.iterator();
    }

    /**
     * 关闭流
     */
    public void close() {
        txt.close();
    }
}
