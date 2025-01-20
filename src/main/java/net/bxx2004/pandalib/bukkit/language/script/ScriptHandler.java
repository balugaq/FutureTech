package net.bxx2004.pandalib.bukkit.language.script;

import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public abstract class ScriptHandler implements ScriptImpl {
    public static HashMap<String, ScriptImpl> handlers = new HashMap<>();

    public static Object run(CommandSender sender, ScriptBox box) {
        if (!handlers.containsKey(box.getIdentifier())) {
            Lang.error("脚本不存在", box.getOrigin());
            return null;
        } else {
            return handlers.get(box.getIdentifier()).scriptRealize(sender, box);
        }
    }

    public static void runs(CommandSender sender, List<ScriptBox> box) {
        for (ScriptBox scriptBox : box) {
            run(sender, scriptBox);
        }
    }

    public static void register(ScriptImpl impl) {
        if (handlers.containsKey(impl.scriptIdentifier())) {
            Lang.error("脚本已经存在", impl.scriptIdentifier());
        } else {
            handlers.put(impl.scriptIdentifier(), impl);
        }
    }
}
