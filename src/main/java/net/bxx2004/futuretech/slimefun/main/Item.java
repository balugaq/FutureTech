package net.bxx2004.futuretech.slimefun.main;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.pandalib.bukkit.item.PItemStack;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import org.bukkit.inventory.ItemStack;

public abstract class Item<T> {
    public Item() {
        if (SlimefunItem.getById(this.getID()) == null) {
            doSomeThing();
            SlimefunItem item;
            if (itemStack() instanceof SlimefunItemStack) {
                SlimefunItemStack s = (SlimefunItemStack) itemStack();
                item = new SlimefunItem(group(), s, type(), recipe());
            } else {
                PItemStack s = (PItemStack) itemStack();
                item = new SlimefunItem(group(), new SlimefunItemStack(this.getClass().getSimpleName(), s.clone()), type(), recipe());
            }
            item.register(FutureTech.instance());
            if (listener() != null) {
                listener().hook("FutureTech");
            }
            if (ConfigManager.enableResearch()) {
                if (research() != null) {
                    research().addItems(item);
                }
            }
        }
    }

    public void doSomeThing() {
    }

    ;

    public abstract T itemStack();

    public abstract ItemGroup group();

    public abstract RecipeType type();

    public abstract ItemStack[] recipe();

    public abstract PListener listener();

    public abstract Research research();

    public SlimefunItem getItem() {
        return SlimefunItem.getById(getID());
    }

    public String getID() {
        return this.getClass().getSimpleName();
    }
}
