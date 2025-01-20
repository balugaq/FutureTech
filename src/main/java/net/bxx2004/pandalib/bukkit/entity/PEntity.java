package net.bxx2004.pandalib.bukkit.entity;

import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

/**
 * 快速创建一个实体
 */
public class PEntity {
    private LivingEntity entity;
    private PEntityMeta meta;
    private Location location;
    private EntityType type;

    /**
     * 构造一个简单的实体
     *
     * @param location   位置
     * @param entityType 实体类型
     */
    public PEntity(Location location, EntityType entityType) {
        this.location = location;
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    }

    /**
     * 构造一个简单的实体
     *
     * @param world      世界名称
     * @param x          X坐标
     * @param y          Y坐标
     * @param z          Z坐标
     * @param entityType 实体类型
     */
    public PEntity(String world, double x, double y, double z, EntityType entityType) {
        this.location = new Location(Bukkit.getWorld(world), x, y, z);
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    }

    /**
     * 构造一个带有PEntityMeta属性的实体
     *
     * @param location   位置
     * @param entityType 实体类型
     * @param meta       实体数据
     */
    public PEntity(Location location, EntityType entityType, PEntityMeta meta) {
        this.meta = meta;
        this.location = location;
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    }

    /**
     * 构造一个带有PEntityMeta属性的实体
     *
     * @param world      世界名称
     * @param x          X坐标
     * @param y          Y坐标
     * @param z          Z坐标
     * @param entityType 实体类型
     * @param meta       实体数据
     */
    public PEntity(String world, double x, double y, double z, EntityType entityType, PEntityMeta meta) {
        this.meta = meta;
        this.location = new Location(Bukkit.getWorld(world), x, y, z);
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    }

    /**
     * 构造一个简单的实体
     *
     * @param type 实体类型
     */
    public PEntity(EntityType type) {
        this.type = type;
    }

    /**
     * 生成实体
     *
     * @param location 位置
     */
    public void spawn(Location location) {
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, type);
    }

    /**
     * 返回PEntity包装的Entity类型
     *
     * @return 实体
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * 添加一个事件
     *
     * @param event PListener
     */
    public void addListener(PListener event) {

    }

    /**
     * 返回这个实体的实体数据
     *
     * @return 实体数据
     */
    public PEntityMeta getMeta() {
        if (this.meta == null) {
            return new PEntityMeta(this.getEntity().getName(), (int) this.getEntity().getHealth(), this.getEntity().getType(), new HashMap<Attribute, Integer>(), new HashMap<PotionEffectType, String>());
        }
        return meta;
    }

    /**
     * 设置这个实体的实体数据
     *
     * @param meta 实体数据
     */
    public void setMeta(PEntityMeta meta) {
        this.meta = meta;
        update(meta);
    }

    /**
     * 返回这个实体的所在位置
     *
     * @return 位置
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 更新这个实体的数据,一般用不到
     */
    private void update(PEntityMeta meta) {
        this.entity.setCustomName(meta.getDisplayName());
        this.entity.setCustomNameVisible(true);
        this.entity.setHealth(meta.getHealth());
        if (meta.getType() != null) {
            this.entity.remove();
            this.entity = (LivingEntity) this.location.getWorld().spawnEntity(location, meta.getType());
            meta.setType(null);
            update(meta);
        }
        HashMap<Attribute, Integer> attribute = meta.getAttribute();
        for (Map.Entry<Attribute, Integer> entry : attribute.entrySet()) {
            entity.getAttribute(entry.getKey()).setBaseValue(entry.getValue());
        }
        HashMap<PotionEffectType, String> potion = meta.getPotionEffect();
        for (Map.Entry<PotionEffectType, String> entry : potion.entrySet()) {
            PotionEffectType type = entry.getKey();
            int time = Integer.parseInt(entry.getValue().split("-")[0]);
            int level = Integer.parseInt(entry.getValue().split("-")[1]);
            entity.addPotionEffect(new PotionEffect(type, (time * 20), level));
        }
    }
}
