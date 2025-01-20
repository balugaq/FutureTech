package net.bxx2004.pandalib.bukkit.entity;

import net.bxx2004.pandalib.bukkit.file.PYml;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 有关于全息投影的类
 */
public class PDisplay {
    private HashMap<Integer, String> content;
    private Entity[] them;
    private int nowLine;
    private double linedistance = 0.25;
    ;
    private Location location;
    private String id;

    public PDisplay() {

    }

    /**
     * 构造一个全息投影类
     */
    public PDisplay(String id) {
        this.id = id;
        this.nowLine = 0;
        this.content = new HashMap<Integer, String>();
    }

    /**
     * 从一个YML文件获取构造器
     *
     * @param name 名字
     * @return 构造器
     */
    public static PDisplay getDisPlayByYML(PYml yml, String name) {
        String id = yml.getString(name + ".id");
        Location location = (Location) yml.get(name + ".location");
        double distance = yml.getDouble(name + ".distance");
        List<String> list = new ArrayList<String>();
        Iterator<String> keys = yml.getYaml().getConfigurationSection(name + ".content").getKeys(false).iterator();
        while (keys.hasNext()) {
            String a = keys.next();
            list.add(yml.getString(name + ".content." + a));
        }
        String[] aa = new String[list.size()];
        list.toArray(aa);
        PDisplay display = new PDisplay(id).inputContent(aa);
        display.setDistance(distance);
        display.spawn(location);
        return display;
    }

    /**
     * 添加文本
     *
     * @param content 文本内容
     * @return 当前构造器
     */
    public PDisplay inputContent(String... content) {
        for (String s : content) {
            this.content.put(nowLine, s);
            this.nowLine += 1;
        }
        return this;
    }

    public PDisplay clear() {
        this.content.clear();
        return this;
    }

    /**
     * 为该全息图设置行距
     *
     * @param distance 行距
     */
    public void setDistance(double distance) {
        this.linedistance = distance;
    }

    /**
     * 在某个位置生成该全息图
     *
     * @param location 位置
     * @return 当前构造器
     */
    public PDisplay spawn(Location location) {
        this.them = new Entity[this.content.keySet().size()];
        callEntity(location);
        return this;
    }

    public void refresh() {
        content.keySet().forEach(s -> {
            replace(s, content.get(s));
        });
    }

    /**
     * 删除这个全息显示
     */
    public void remove() {
        for (Entity e : them) {
            e.getScoreboardTags().forEach(s -> {
                if (s.contains(id)) {
                    e.remove();
                }
            });
        }
    }

    /**
     * 传送该全息到某位置
     *
     * @param location 位置
     */
    public void teleport(Location location) {
        double o = 0;
        for (Entity entity : them) {
            entity.teleport(location.clone().add(0.00, o, 0.00));
            o -= linedistance;
        }
    }

    public void replace(int line, String c) {
        them[line].setCustomName(c.replaceAll("&", "§"));
        content.put(line, c.replaceAll("&", "§"));
    }

    private void callEntity(Location location) {
        this.location = location;
        double o = 0;
        for (int i = 0; i < content.keySet().size(); i++) {
            ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0.00, o, 0.00), EntityType.ARMOR_STAND);
            entity.setVisible(false);
            entity.setCustomNameVisible(true);
            entity.setCustomName(content.get(i).replaceAll("&", "§"));
            entity.setGravity(false);
            entity.setSmall(true);
            entity.setInvulnerable(true);
            entity.setMarker(true);
            //entity.setCanTick(false);
            entity.addScoreboardTag(id);
            this.them[i] = entity;
            o -= linedistance;
        }
    }

    public String getId() {
        return id;
    }

    /**
     * 将此构造器保存到YML文件
     *
     * @param yml  文件
     * @param name 名字
     */
    public void saveDisPlayByYML(PYml yml, String name) {
        yml.set(name + ".id", this.id);
        yml.set(name + ".location", this.location);
        yml.set(name + ".content", this.content);
        yml.set(name + ".distance", this.linedistance);
    }

    private PDisplay getInstance() {
        return this;
    }
}
