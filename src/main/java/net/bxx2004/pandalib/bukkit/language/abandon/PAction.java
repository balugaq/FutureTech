package net.bxx2004.pandalib.bukkit.language.abandon;

import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * 执行特殊的动作
 *
 * @since 1.5.3
 */
@Deprecated
public abstract class PAction {
    public static HashMap<String, PActionObject> map = new HashMap<String, PActionObject>();
    private String name;

    public PAction(String word) {
        String key = word.toLowerCase();
        if (!map.keySet().contains(key)) {
            map.put(key, new PActionObject() {
                @Override
                Object vaule(Player player1, String... args) {
                    return run(player1, args);
                }
            });
            this.name = word;
        } else {
            Lang.print("&c[&fPAction&c] &c无法为该动作 &f" + key + " &c注册,因为一个存在了一个名称相同的动作...");
        }
    }

    /**
     * 执行语法
     *
     * @param word   语法
     * @param player 玩家
     */
    public static Object go(String word, Player player) {
        try {
            if (word.startsWith("~~~")) {
                return null;
            }
            return goWord(word.replaceAll("<PLAYER>", player.getName()), player);
        } catch (Exception e) {
            Lang.error("&4PandaScript运行时错误", word);
            e.printStackTrace();
        }
        return null;
    }

    public static String origin(String... args) {
        String b = "";
        for (String a : args) {
            b = b + a + " ";
        }
        b = b.substring(0, b.length() - 1);
        return b.replaceAll("\\\t", "").replaceAll("\\\n", "");
    }

    /**
     * 该语句是否存在
     *
     * @param word 语句
     * @return 是否存在
     */
    public static boolean has(String word) {
        return map.keySet().contains(word.toLowerCase());
    }

    /**
     * 用于执行单条语句
     *
     * @param word   语句
     * @param player 玩家
     * @return 执行结果
     */
    private static Object goWord(String word, Player player) {
        if (word.startsWith("$")) {
            return PandaLib.data.get(word.replaceAll("\\$", ""));
        }
        String words = word.split(" ")[0].toLowerCase();
        String[] args = word.split(" ");
        String[] args1 = new String[args.length - 1];
        for (int i = 0; i < args.length; i++) {
            if (i >= args1.length) {
                continue;
            } else {
                args1[i] = args[i + 1];
            }
        }
        if (!map.keySet().contains(words) && (!word.trim().startsWith("_"))) {
            Lang.print("&c[&fPAction&c] &c执行失败,该动作 &f" + words + " &c无效...");
        } else {
            return (map.get(words).vaule(player, args1));
        }
        return "[Null]";
    }

    public abstract Object run(Player player, String... args);

    public String getName() {
        return this.name;
    }
}

@Deprecated
abstract class PActionObject {
    abstract Object vaule(Player player, String... args);
}