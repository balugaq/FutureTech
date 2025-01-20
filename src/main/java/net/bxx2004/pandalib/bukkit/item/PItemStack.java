package net.bxx2004.pandalib.bukkit.item;

import net.bxx2004.pandalib.bukkit.item.nbt.NBTMeta;
import net.bxx2004.pandalib.bukkit.util.PMath;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 构造一个自定义物品堆
 */
public class PItemStack extends ItemStack {
    private PItemStackTheme theme;
    private List<String> scripts;

    /**
     * 无用
     */
    protected PItemStack() {

    }

    public PItemStack(String playerID, String name, String... lore) {
        super(new ItemStack(Material.PLAYER_HEAD));
        SkullMeta meta = (SkullMeta) super.getItemMeta();
        meta.setOwner(playerID);
        meta.setDisplayName(name.replaceAll("&", "§"));
        if (lore != null) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < lore.length; i++) {
                list.add(lore[i].replaceAll("&", "§"));
            }
            meta.setLore(list);
        }
        super.setItemMeta(meta);
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param type 材质
     */
    public PItemStack(Material type) {
        super(new ItemStack(type));
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param type   材质
     * @param amount 数量
     */
    public PItemStack(Material type, int amount) {
        super(new ItemStack(type, amount));
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param stack ItemStack物品堆
     */
    public PItemStack(ItemStack stack) {
        super(stack);
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param type   材质
     * @param amount 数量
     * @param name   物品堆显示名称
     * @param lore   物品堆的描述
     */
    public PItemStack(Material type, int amount, String name, String... lore) {
        super(new ItemStack(type, amount));
        ItemMeta meta = super.getItemMeta();
        meta.setDisplayName(name.replaceAll("&", "§"));
        if (lore != null) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < lore.length; i++) {
                list.add(lore[i].replaceAll("&", "§"));
            }
            meta.setLore(list);
        }
        super.setItemMeta(meta);
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param type 材质
     * @param name 物品堆显示名称
     * @param lore 物品堆的描述
     */
    public PItemStack(Material type, String name, String... lore) {
        super(new ItemStack(type, 1));
        ItemMeta meta = super.getItemMeta();
        meta.setDisplayName(name.replaceAll("&", "§"));
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < lore.length; i++) {
            list.add(lore[i].replaceAll("&", "§"));
        }
        meta.setLore(list);
        super.setItemMeta(meta);
    }

    /**
     * 构造一个自定义物品堆
     *
     * @param type     材质
     * @param name     物品堆显示名称
     * @param lore     物品堆的描述
     * @param enchants 物品堆的附魔(名称-级别)
     */
    public PItemStack(Material type, String name, String[] lore, String[] enchants) {
        super(new ItemStack(type, 1));
        ItemMeta meta = super.getItemMeta();
        meta.setDisplayName(name.replaceAll("&", "§"));
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < lore.length; i++) {
            list.add(lore[i].replaceAll("&", "§"));
        }
        meta.setLore(list);
        Map<Enchantment, Integer> map = new HashMap<>();
        for (String ench : enchants) {
            String enchname = ench.split("-")[0];
            int level = Integer.parseInt(ench.split("-")[1]);
            map.put(Enchantment.getByName(enchname), level);
        }
        super.setItemMeta(meta);
        super.addUnsafeEnchantments(map);
    }

    /**
     * 从一个字符串特定格式读取一个CustomItem对象(适用Yaml)
     *
     * @param s 字符串
     * @return CustomItem
     * @since 1.5.3
     */
    @Deprecated
    public static PItemStack From(String s) {
        BufferedReader reader = new BufferedReader(new StringReader(s));
        FileConfiguration data = YamlConfiguration.loadConfiguration(reader);

        PItemStack item = new PItemStack(Material.getMaterial(data.getString("Material")), data.getInt("Amount"), data.getString("DisPlayName").replaceAll("&", "§"));
        if ((data.getStringList("Lore") != null) && (!data.getStringList("Lore").isEmpty())) {
            List<String> lore = data.getStringList("Lore");
            for (String a : lore) {
                item.addLore(a.replaceAll("&", "§"));
            }
        }
        if ((data.getStringList("Enchant") != null) && (!data.getStringList("Enchant").isEmpty())) {
            HashMap<Enchantment, Integer> enchant = new HashMap<Enchantment, Integer>();
            for (String e : data.getStringList("Enchant")) {
                enchant.put(Enchantment.getByName(e.split(":")[0]), Integer.parseInt(e.split(":")[1]));
            }
            item.addUnsafeEnchantments(enchant);
        }
        if ((data.getStringList("Flags") != null) && (!data.getStringList("Flags").isEmpty())) {
            for (String b : data.getStringList("Flags")) {
                // item.addItemFlags(ItemFlag.valueOf(b));
            }
        }
        if ((data.getStringList("Scripts") != null) && (!data.getStringList("Scripts").isEmpty())) {
            item.setScripts(data.getStringList("Scripts"));
        }
        return item;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public PItemStack resetMeta(NBTMeta meta) {
        //停工(!!!)(!!!)(!!!)
        return null;
    }

    public void setTheme(PItemStackTheme theme) {
        this.theme = theme;
    }

    public void setContsent(String content) {
        theme.set(content);
        ItemMeta meta = super.getItemMeta();
        meta.setLore(PMath.toStringList(theme.getLore()));
        super.setItemMeta(meta);
    }

    public NBTMeta nbtMeta() {
        return new NBTMeta(this);
    }

    public void drop(Location location) {
        location.getWorld().dropItem(location, this);
    }

    /**
     * 为该物品增加Lore
     *
     * @param lore 描述
     * @since 1.4.2
     */
    public void addLore(String... lore) {
        ItemMeta meta = super.getItemMeta();
        if (meta.hasLore()) {
            List<String> list = meta.getLore();
            for (int i = 0; i < lore.length; i++) {
                list.add(lore[i].replaceAll("&", "§"));
            }
            meta.setLore(list);
            super.setItemMeta(meta);
        } else {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < lore.length; i++) {
                list.add(lore[i].replaceAll("&", "§"));
            }
            meta.setLore(list);
            super.setItemMeta(meta);
        }
    }

    /**
     * 为物品移除某一行lore
     *
     * @param index 行数
     * @since 1.5.2
     */
    public void removeLore(int index) {
        ItemMeta meta = super.getItemMeta();
        List<String> list = meta.getLore();
        list.remove(index);
        meta.setLore(list);
        super.setItemMeta(meta);
    }

    /**
     * 根据正则表达式匹配Lore
     *
     * @param regx 正则表达式
     */
    public void searchLore(String regx, Consumer<String> consumer) {
        Pattern pattern = Pattern.compile(regx);
        for (String lore : super.getItemMeta().getLore()) {
            Matcher m = pattern.matcher(lore);
            while (m.find()) {
                consumer.accept(m.group());
            }
        }
    }

    /**
     * @return 字符串
     * @since 1.5.3
     * 将这个物品对象转化为字符串形式(适用Yaml)
     */
    @Deprecated
    public String To() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DisPlayName: " + getItemMeta().getDisplayName() + "\n" +
                "Material: " + getType() + "\n" +
                "Amount: " + getAmount());
        if (getItemMeta().hasLore()) {
            buffer.append("\nLore:");
            for (String s : getItemMeta().getLore()) {
                buffer.append("\n  - \"" + s + "\"");
            }
        }
        if (getItemMeta().hasEnchants()) {
            buffer.append("\nEnchant:");
            for (Enchantment s : getItemMeta().getEnchants().keySet()) {
                buffer.append("\n  - \"" + s.getName() + ":" + getItemMeta().getEnchants().get(s) + "\"");
            }
        }
        if ((getItemMeta().getItemFlags() != null) && (getItemMeta().getItemFlags().size() != 0)) {
            buffer.append("\nFlags:");
            for (ItemFlag flag : getItemMeta().getItemFlags()) {
                buffer.append("\n  - \"" + flag + "\"");
            }
        }
        if (getScripts() != null) {
            buffer.append("\nScripts:");
            for (String flag : getScripts()) {
                buffer.append("\n  - \"" + flag + "\"");
            }
        }
        return buffer.toString();
    }
}
