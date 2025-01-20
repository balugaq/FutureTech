package net.bxx2004.pandalib.bukkit.entity;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/**
 * PEntity的属性类
 */
public class PEntityMeta {
    private String displayName;
    private int health;
    private HashMap<Attribute, Integer> attribute;
    private HashMap<PotionEffectType, String> potionEffect;
    private EntityType type;

    /**
     * 构造一个PEntity属性类
     *
     * @param displayName  显示名称
     * @param health       血量
     * @param type         实体种类
     * @param attribute    实体属性
     * @param potionEffect 实体效果
     */
    public PEntityMeta(String displayName, int health, EntityType type, HashMap<Attribute, Integer> attribute, HashMap<PotionEffectType, String> potionEffect) {
        this.displayName = displayName;
        this.health = health;
        this.type = type;
        this.attribute = attribute;
        this.potionEffect = potionEffect;
    }

    /**
     * 将此属性给定PEntity
     *
     * @param entity PEntity实体
     */
    public void addEntity(PEntity entity) {
        entity.setMeta(this);
    }

    /**
     * 给这个实体添加Attribute属性
     *
     * @param attribute 属性
     * @param value     值
     */
    public void addAttribute(Attribute attribute, int value) {
        this.attribute.put(attribute, value);
    }

    /**
     * 移除这个实体的某个Attribute属性
     *
     * @param attribute 属性
     */
    public void removeAttribute(Attribute attribute) {
        this.attribute.remove(attribute);
    }

    /**
     * 清空这个实体的所有Attribute属性
     */
    public void clearAttribute() {
        this.attribute.clear();
    }

    /**
     * 给这个实体添加PotionEffect属性 (values应该填写 [持续时间-效果等级])
     *
     * @param potionEffect 属性
     * @param values       值 (values应该填写 [持续时间-效果等级])
     */
    public void addPotionEffect(PotionEffectType potionEffect, String values) {
        this.potionEffect.put(potionEffect, values);
    }

    /**
     * 移除这个实体的某个PotionEffect属性
     *
     * @param potionEffect 属性
     */
    public void removePotionEffect(PotionEffectType potionEffect) {
        this.potionEffect.remove(potionEffect);
    }

    /**
     * 清空这个实体的所有PotionEffect属性
     */
    public void clearPotionEffect() {
        this.potionEffect.clear();
    }

    /**
     * 返回这个实体属性的显示名称
     *
     * @return 显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置这个实体属性的显示名称
     *
     * @param displayName 显示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 返回这个实体属性的血量
     *
     * @return 血量
     */
    public int getHealth() {
        return health;
    }

    /**
     * 设置这个实体属性的血量
     *
     * @param health 血量
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * 返回这个实体的Attribute属性Map
     *
     * @return 属性
     */
    public HashMap<Attribute, Integer> getAttribute() {
        return attribute;
    }

    /**
     * 返回这个实体的PotionEffect属性Map
     *
     * @return 属性
     */
    public HashMap<PotionEffectType, String> getPotionEffect() {
        return potionEffect;
    }

    /**
     * 返回这个实体类型
     *
     * @return 实体类型
     */
    public EntityType getType() {
        return type;
    }

    /**
     * 设置这个实体的类型
     *
     * @param type 实体类型
     */
    public void setType(EntityType type) {
        this.type = type;
    }
}
