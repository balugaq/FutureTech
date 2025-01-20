package net.bxx2004.futuretech.slimefun;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import net.bxx2004.futuretech.FutureTech;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Tools {
    private static final String LORE_PREFIX = ChatColors.color("&8⇨ &e⚡ &7");
    private static final Pattern REGEX = Pattern.compile(ChatColors.color("(&c&o)?" + LORE_PREFIX) + "[0-9.]+ / [0-9.]+ J");
    ;
    private static HashMap<String, InventoryHolder> map = new HashMap<>();

    public static NamespacedKey key(String id) {
        NamespacedKey key = new NamespacedKey(FutureTech.instance(), id);
        return key;
    }

    public static InventoryHolder holder(String id) {
        return map.get(id);
    }

    public static InventoryHolder setHolder(String id) {
        map.put(id, new InventoryHolder() {
            @Override
            public @NotNull Inventory getInventory() {
                return null;
            }
        });
        return map.get(id);
    }

    public static ItemMeta setCharge(@Nonnull ItemMeta meta, float charge, float capacity) {
        BigDecimal decimal = BigDecimal.valueOf((double) charge).setScale(2, RoundingMode.HALF_UP);
        float value = decimal.floatValue();
        NamespacedKey key = Slimefun.getRegistry().getItemChargeDataKey();
        meta.getPersistentDataContainer().set(key, PersistentDataType.FLOAT, value);
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList();

        for (int i = 0; i < ((List) lore).size(); ++i) {
            String line = (String) ((List) lore).get(i);
            if (REGEX.matcher(line).matches()) {
                ((List) lore).set(i, LORE_PREFIX + value + " / " + capacity + " J");
                meta.setLore((List) lore);
                return meta;
            }
        }

        ((List) lore).add(LORE_PREFIX + value + " / " + capacity + " J");
        meta.setLore((List) lore);
        return meta;
    }

    public static float getCharge(@Nonnull ItemMeta meta) {
        Validate.notNull(meta, "Meta cannot be null!");
        NamespacedKey key = Slimefun.getRegistry().getItemChargeDataKey();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Float value = (Float) container.get(key, PersistentDataType.FLOAT);
        if (value != null) {
            return value;
        } else {
            if (meta.hasLore()) {
                Iterator var4 = meta.getLore().iterator();

                while (var4.hasNext()) {
                    String line = (String) var4.next();
                    if (REGEX.matcher(line).matches()) {
                        String data = ChatColor.stripColor(PatternUtils.SLASH_SEPARATOR.split(line)[0].replace(LORE_PREFIX, ""));
                        float loreValue = Float.parseFloat(data);
                        container.set(key, PersistentDataType.FLOAT, loreValue);
                        return loreValue;
                    }
                }
            }

            return 0.0F;
        }
    }
}
