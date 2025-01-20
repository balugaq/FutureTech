package net.bxx2004.futuretech.slimefun.main.items.object;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.machine.FT_MAKER;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import net.bxx2004.pandalib.bukkit.putil.PMath;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RegisterItem
public class FT_COPPERSWORD extends Item<PItemStack> {
    public FT_COPPERSWORD() {
        super();
    }
    @Override
    public PItemStack itemStack() {
        PItemStack stack = new PItemStack(Material.IRON_SWORD,
                ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
        stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,3);
        return stack;
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.OBJECT;
    }

    @Override
    public RecipeType type() {
        RecipeType t = new RecipeType(new FT_MAKER().item().clone(),"FT_MAKER");
        return t;
    }

    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                null,null,null,
                null,new PItemStack(Material.BOOK,"&e在指导书内查看配方"),null,
                null,null,null
        };
    }

    @Override
    public PListener listener() {
        return new PListener(){
            @EventHandler
            public void onDamage(EntityDamageByEntityEvent event){
                if ((event.getDamager() instanceof Player) && (event.getEntity() instanceof LivingEntity)){
                    Player player = (Player) event.getDamager();
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    if (SlimefunItem.getByItem(player.getItemInUse()) != null){
                        if (SlimefunItem.getByItem(player.getItemInUse()).getId().equalsIgnoreCase(getID())){
                            if (PMath.getRandomAsInt(0,100) < 10){
                                entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60,1));
                            }
                        }
                    }
                }
            }
        };
    }

    @Override
    public Research research() {
        return SlimefunFactory.ROBOT_R;
    }
}
