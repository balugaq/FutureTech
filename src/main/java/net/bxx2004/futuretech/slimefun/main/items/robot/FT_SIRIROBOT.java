package net.bxx2004.futuretech.slimefun.main.items.robot;

import com.google.gson.Gson;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.unirest.json.JSONObject;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.futuretech.slimefun.main.machine.FT_MAKER;
import net.bxx2004.java.reflect.PJVariable;
import net.bxx2004.pandalib.bukkit.pfile.PMenuBuilder;
import net.bxx2004.pandalib.bukkit.pfile.PPds;
import net.bxx2004.pandalib.bukkit.pfile.PTxt;
import net.bxx2004.pandalib.bukkit.pfile.PYml;
import net.bxx2004.pandalib.bukkit.pgui.PPageGui;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.planguage.PMessage;
import net.bxx2004.pandalib.bukkit.planguage.PTitle;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
@RegisterItem
public class FT_SIRIROBOT extends Item<SlimefunItemStack> {
    public FT_SIRIROBOT(){
        super();
    }

    @Override
    public SlimefunItemStack itemStack() {
        return new SlimefunItemStack("FT_SIRIROBOT","890b1cd0cb10dcc3e99bf4104b10360c9279fa0a2aa7bded1483359b0474e11e", ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.ROBOT;
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
                null,new PItemStack(Material.BOOK,"&E在指导书内查看配方"),null,
                null,null,null
        };
    }

    @Override
    public PListener listener() {
        return new PListener(){
            @EventHandler
            public void onChat(AsyncPlayerChatEvent event){
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        Location location = event.getPlayer().getLocation();
                        int radius = 10;
                        for(int x = -radius; x <= radius; ++x) {
                            for(int y = -radius; y <= radius; ++y) {
                                for(int z = -radius; z <= radius; ++z) {
                                    Block blockAt = event.getPlayer().getWorld().getBlockAt(location.clone().add((double)x, (double)y, (double)z));
                                    if (BlockStorage.hasBlockInfo(blockAt)) {
                                        if (BlockStorage.check(blockAt).getId().equalsIgnoreCase("FT_SIRIROBOT")){
                                            if (event.getMessage().equalsIgnoreCase("Hi Siri")){
                                                PMenuBuilder builder = new PMenuBuilder("FutureTech",new PYml("plugins/FutureTech/scripts/FT_SIRIROBOT.yml",false));
                                                builder.open(event.getPlayer(),true);
                                                return;
                                            }
                                            if (event.getMessage().contains("Hi Siri")){
                                                PTitle.To(event.getPlayer(),"服务器可能会响应过慢,请耐心等候...");
                                                String message = event.getMessage().replaceAll("Hi Siri","").replaceAll(" ","");
                                                replay(message, event.getPlayer(), blockAt.getLocation().getNearbyEntities(10,10,10));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }.runTask(FutureTech.instance());
            }
        };
    }
    private void replay(String message, Player player,Collection<Entity> entityCollection){
        new BukkitRunnable(){
            @Override
            public void run() {
                try {
                    StringBuffer content = new StringBuffer();
                    URL url = new URL("http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + message);
                    InputStream input = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        content.append(line);
                    }
                    JSONObject jsonObject=new JSONObject(content.toString());
                    String replyMessage = jsonObject.getString("content");
                    for (Entity entity : entityCollection){
                        if (entity instanceof Player){
                            PMessage.to((Player) entity,"&cSiri &f对 &c" +player.getName() + " &f说: &9" + replyMessage);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(FutureTech.instance());
    }
    @Override
    public Research research() {
        return SlimefunFactory.ROBOT_R;
    }
}
