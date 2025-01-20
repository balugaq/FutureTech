package net.bxx2004.pandalib.bukkit.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Set;

/**
 * 冷却计时器
 */
public class PCooldown {
    private Plugin plugin;
    private HashMap<String, HashMap<OfflinePlayer, Float>> map;

    /**
     * 构建一个冷却组
     *
     * @param plugin 插件
     */
    public PCooldown(Plugin plugin) {
        this.map = new HashMap<String, HashMap<OfflinePlayer, Float>>();
        this.plugin = plugin;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (map.keySet().size() == 0) {

                } else {
                    for (String key : map.keySet()) {
                        HashMap<OfflinePlayer, Float> map1 = map.get(key);
                        if (map1.keySet().size() == 0) {

                        } else {
                            for (OfflinePlayer key1 : map1.keySet()) {
                                if (map1.get(key1) <= 0) {
                                    map1.remove(key1);
                                } else {
                                    map1.replace(key1, (float) (map1.get(key1) - 0.05));
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }

    /**
     * 克隆该组
     *
     * @return 克隆后的
     */
    public PCooldown clone() {
        return this;
    }

    /**
     * 在该对象添加一个冷却组
     *
     * @param key 唯一值
     * @return 是否成功
     */
    public boolean addKey(String key) {
        try {
            map.put(key, new HashMap<OfflinePlayer, Float>());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 在该对象删除一个冷却组
     *
     * @param key 唯一值
     * @return 是否成功
     */
    public boolean removeKey(String key) {
        try {
            map.remove(key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回在该对象内所有冷却组的唯一值
     *
     * @return 在该对象内所有冷却组的唯一值
     */
    public Set<String> keySet() {
        return map.keySet();
    }

    /**
     * 向该对象内的唯一对应值添加玩家冷却时间
     *
     * @param key    唯一值
     * @param player 玩家
     * @param value  冷却值
     * @return 是否成功
     */
    public boolean add(String key, OfflinePlayer player, float value) {
        try {
            map.get(key).put(player, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addCoolDownOfKey(String key, OfflinePlayer player, float value) {
        return add(key, player, value);
    }

    /**
     * 向该对象内的唯一对应值删除玩家冷却时间
     *
     * @param key    唯一值
     * @param player 玩家
     * @return 是否成功
     */
    public boolean remove(String key, OfflinePlayer player) {
        try {
            map.get(key).remove(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 向该对象内的唯一对应值修改玩家冷却时间
     *
     * @param key    唯一值
     * @param player 玩家
     * @param value  冷却值
     * @return 是否成功
     */
    public boolean set(String key, OfflinePlayer player, float value) {
        try {
            map.get(key).replace(player, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询该对象内的唯一对应值玩家冷却时间
     *
     * @param key    唯一值
     * @param player 玩家
     * @return 值
     */
    public float look(String key, OfflinePlayer player) {
        try {
            if (map.get(key).get(player) == null || map.get(key).get(player) <= 0) {
                return 0;
            }
            return map.get(key).get(player);
        } catch (Exception e) {
            return 0;
        }
    }

    public float lookCoolDownOfKey(String key, OfflinePlayer player) {
        return look(key, player);
    }

    /**
     * 返回该对象内的唯一对应值的所有玩家冷却
     *
     * @param key 唯一值
     * @return 该对象内的唯一对应值的所有玩家冷却
     */
    public HashMap<OfflinePlayer, Float> all(String key) {
        return map.get(key);
    }

    /**
     * 判断某一组内玩家是否处于冷却
     *
     * @param key    值
     * @param player 玩家
     * @return 某一组内玩家是否处于冷却
     */
    public boolean is(String key, OfflinePlayer player) {
        if (map.get(key).get(player) == null || map.get(key).get(player) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCoolDown(String key, OfflinePlayer player) {
        return is(key, player);
    }
}
