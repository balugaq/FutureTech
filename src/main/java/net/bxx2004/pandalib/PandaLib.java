package net.bxx2004.pandalib;

import net.bxx2004.pandalib.bukkit.file.PYml;
import net.bxx2004.pandalib.bukkit.language.abandon.PAction;
import net.bxx2004.pandalib.bukkit.language.abandon.PActionBar;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.language.abandon.PTitle;
import net.bxx2004.pandalib.bukkit.language.abandon.pactionextend.PlayerControl;
import net.bxx2004.pandalib.bukkit.util.PMath;
import net.bxx2004.pandalib.java.reflect.PJMethod;
import net.bxx2004.pandalibloader.PandaLibPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PandaLib {
    public static final String VERSION = "4.0.0";
    public static Plugin initPlugin;
    public static PandaLibPlugin initPandaLibPlugin;
    public static HashMap<String, String> data = new HashMap<>();
    private static PYml option;
    private static boolean isInit = false;

    public static void init(PandaLibPlugin plugin) {
        if (!isInit) {
            initPandaLibPlugin = plugin;
            initPlugin = (Plugin) plugin.getPlugin();
            isInit = true;
            File file = new File(plugin.getPath().split("plugins")[0] + "server");
            if (!file.exists()) {
                file.mkdirs();
            }
            option = new PYml(file.getAbsolutePath() + "/option.yml", true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    registerAction();
                }
            }.run();
        }
    }

    public static File PATH() {
        return new File(initPlugin.getDataFolder().getAbsolutePath().split("plugins")[0] + "server");
    }

    public static String getServerLanguage() {
        if (option.getString("__option__.language") == null) {
            option.set("__option__.language", "zh_CN");
            option.reloadYaml();
            return "zh_CN";
        } else {
            return option.getString("__option__.language");
        }
    }

    /**
     * 获取某个插件的plugin.yml文件
     *
     * @param plugin 插件
     * @return plugin.yml
     */
    public static FileConfiguration getLoadYmlFromPlugin(PandaLibPlugin plugin) {
        return YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getJarFile("plugin.yml")));
    }

    /**
     * 跨服
     *
     * @param player 玩家
     * @param server 服务器
     */
    public static void connect(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(initPlugin, "BungeeCord", b.toByteArray());
    }

    /**
     * 从某个插件的Jar中释放文件
     *
     * @param plugin   插件
     * @param filePath 文件路径
     * @param outPath  释放路径
     */
    public static void saveFileFormPlugin(PandaLibPlugin plugin, String filePath, String outPath) {
        try {
            InputStreamReader input = new InputStreamReader(plugin.getJarFile(filePath), "UTF-8");
            File file = new File(outPath);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), true);
            BufferedReader reder = new BufferedReader(input);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Iterator<String> i = reder.lines().iterator();
            while (i.hasNext()) {
                writer.println(i.next());
            }
            input.close();
            writer.close();
            reder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerAction() {
        new PlayerControl();
        //staticreflet 类名 方法名 [参数,a,b,c]
        new PAction("staticreflect") {
            @Override
            public Object run(Player player, String... args) {
                try {
                    final Object[] re = new Object[1];
                    PJMethod method = new PJMethod(Class.forName(args[0]));
                    String origin = PAction.origin(args);
                    PMath.regx("\\[.*\\]", origin,
                            s -> {
                                String word = s.replaceAll("\\[", "").replaceAll("]", "");
                                Object[] ar = new Object[word.split(",").length];
                                int i = 0;
                                for (String a : word.split(",")) {
                                    if (a.contains("$")) {
                                        ar[i] = PAction.go(a, player);
                                    } else {
                                        ar[i] = a;
                                    }
                                }
                                re[0] = method.InPutName(args[1]).InPutArg(ar).run(null);
                            }
                    );
                    return re[0];
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        //operate (+ - * /) [1,2,3,4,5,6,7,8,9,10,11,12,13]
        new PAction("operate") {
            @Override
            public Object run(Player player, String... args) {
                String type = args[0];
                final String[] result = {""};
                String origin = PAction.origin(args);
                PMath.regx("\\[.*\\]", origin,
                        s -> {
                            String word = s.replaceAll("\\[", "").replaceAll("]", "");
                            for (String a : word.split(",")) {
                                if (a.contains("$")) {
                                    if (type.equals("+")) {
                                        result[0] = result[0] + PAction.go(a, player).toString();
                                    }
                                    if (type.equals("-")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) - Double.valueOf(PAction.go(a, player).toString()));
                                    }
                                    if (type.equals("*")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) * Double.valueOf(PAction.go(a, player).toString()));
                                    }
                                    if (type.equals("/")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) / Double.valueOf(PAction.go(a, player).toString()));
                                    }

                                } else {
                                    if (type.equals("+")) {
                                        result[0] = result[0] + a;
                                    }
                                    if (type.equals("-")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) - Double.valueOf(a));
                                    }
                                    if (type.equals("*")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) * Double.valueOf(a));
                                    }
                                    if (type.equals("/")) {
                                        result[0] = String.valueOf(Double.valueOf(result[0]) / Double.valueOf(a));
                                    }
                                }
                            }
                        }
                );
                return result[0];
            }
        };
        //after 0 []
        new PAction("after") {
            @Override
            public Object run(Player player, String... args) {
                int time = Integer.parseInt(args[0]);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        String origin = PAction.origin(args);
                        PMath.regx("\\[.*\\]", origin,
                                s -> {
                                    String word = s.replaceAll("\\[", "").replaceAll("]", "");
                                    if (word.contains(" => ")) {
                                        for (String a : word.split(" => ")) {
                                            PAction.go(a, player);
                                        }
                                    } else {
                                        PAction.go(word, player);
                                    }
                                }
                        );
                    }
                }.runTaskLater(initPlugin, time * 20);
                return null;
            }
        };
        //while () [a => b => c]
        new PAction("while") {
            @Override
            public Object run(Player player, String... args) {
                String origin = PAction.origin(args);
                List<String> body = new ArrayList<>();
                PMath.regx("\\[.*\\]", origin,
                        s -> {
                            body.add(s.replaceAll("\\[", "").replaceAll("]", ""));
                        }
                );
                PMath.regx("\\(.*\\)", origin,
                        con -> {
                            while (PMath.sum(player, con.replaceAll("\\(", "").replaceAll("\\)", ""))) {
                                if (body.get(0).contains(" => ")) {
                                    for (String a : body.get(0).split(" => ")) {
                                        PAction.go(a, player);
                                    }
                                } else {
                                    PAction.go(body.get(0), player);
                                }
                            }
                        }
                );
                return null;
            }
        };
        //for 5 [a => b => c]
        new PAction("for") {
            @Override
            public Object run(Player player, String... args) {
                int t = Integer.parseInt(args[0]);
                String origin = PAction.origin(args);
                for (int i = 0; i < t; i++) {
                    PMath.regx("\\[.*\\]", origin,
                            s -> {
                                String word = s.replaceAll("\\[", "").replaceAll("]", "");
                                if (word.contains(" => ")) {
                                    for (String a : word.split(" => ")) {
                                        PAction.go(a, player);
                                    }
                                } else {
                                    PAction.go(word, player);
                                }
                            }
                    );
                }
                return null;
            }
        };
        //run [a => b => c]
        new PAction("run") {
            @Override
            public Object run(Player player, String... args) {
                String origin = PAction.origin(args);
                PMath.regx("\\[.*\\]", origin,
                        s -> {
                            String word = s.replaceAll("\\[", "").replaceAll("]", "");
                            if (word.contains(" => ")) {
                                for (String a : word.split(" => ")) {
                                    PAction.go(a, player);
                                }
                            } else {
                                PAction.go(word, player);
                            }
                        }
                );
                return null;
            }
        };
        //if () [a => a1] : [b => b1]
        new PAction("if") {
            @Override
            public Object run(Player player, String... args) {
                String origin = PAction.origin(args);
                List<String> body = new ArrayList<>();
                PMath.regx("\\[.*\\]", origin,
                        s -> {
                            for (String a : s.replaceAll("\\[", "").replaceAll("]", "").split(" : ")) {
                                body.add(a);
                            }
                        }
                );
                PMath.regx("\\(.*\\)", origin,
                        con -> {
                            if (PMath.sum(player, con.replaceAll("\\(", "").replaceAll("\\)", ""))) {
                                if (body.get(0).contains(" => ")) {
                                    for (String a : body.get(0).split(" => ")) {
                                        PAction.go(a, player);
                                    }
                                } else {
                                    PAction.go(body.get(0), player);
                                }
                            } else {
                                if (body.get(1).contains(" => ")) {
                                    for (String a : body.get(1).split(" => ")) {
                                        PAction.go(a, player);
                                    }
                                } else {
                                    PAction.go(body.get(1), player);
                                }
                            }
                        }
                );
                return null;
            }
        };
        //check $a > $b
        new PAction("check") {
            @Override
            public Object run(Player player, String... args) {
                String a = String.valueOf(PAction.go(args[0], player));
                String b = String.valueOf(PAction.go(args[2], player));
                switch (args[1]) {
                    case ">":
                        return a.compareTo(b) > 0;
                    case ">=":
                        return a.compareTo(b) >= 0;
                    case "<":
                        return a.compareTo(b) < 0;
                    case "<=":
                        return a.compareTo(b) <= 0;
                    case "==":
                        return a.compareTo(b) == 0;
                }
                return false;
            }
        };
        //var a = some thing..
        new PAction("var") {
            @Override
            public Object run(Player player, String... args) {
                if (args.length >= 3) {
                    if (args[2].startsWith("_")) {
                        data.put(args[0], args[2].substring(1));
                    } else {
                        data.put(args[0], String.valueOf(PAction.go(PAction.origin(args).split(" = ")[1], player)));
                    }
                }
                return null;
            }
        };
        //value a
        new PAction("value") {
            @Override
            public Object run(Player player, String... args) {
                return data.get(args[0]);
            }
        };
        new PAction("tell") {
            @Override
            public Object run(Player player, String... args) {
                if (args[0].contains("$")) {
                    PMessage.to(player, PAction.go(args[0], player).toString());
                    return null;
                }
                PMessage.to(player, args[0]);
                return null;
            }
        };
        new PAction("title") {
            @Override
            public Object run(Player player, String... args) {
                if (args[0].contains("$")) {
                    if (args.length > 1) {
                        PTitle.To(player, PAction.go(args[0], player).toString() + "&nbsp" + PAction.go(args[1], player).toString());
                    } else {
                        PTitle.To(player, PAction.go(args[0], player).toString());
                    }
                    return null;
                }

                if (args.length > 1) {
                    PTitle.To(player, args[0] + "&nbsp" + args[1]);
                } else {
                    PTitle.To(player, args[0]);
                }
                return null;
            }
        };
        new PAction("actionbar") {
            @Override
            public Object run(Player player, String... args) {
                if (args[0].contains("$")) {
                    PActionBar.To(player, PAction.go(args[0], player).toString());
                    return null;
                }
                PActionBar.To(player, args[0]);
                return null;
            }
        };
        new PAction("close") {
            @Override
            public Object run(Player player, String... args) {
                player.closeInventory();
                return null;
            }
        };
        new PAction("print") {
            @Override
            public Object run(Player player, String... args) {
                if (args[0].contains("$")) {
                    Bukkit.getConsoleSender().sendMessage(PAction.go(args[0], player).toString().replaceAll("&", "§"));
                    return null;
                }
                Bukkit.getConsoleSender().sendMessage(args[0].replaceAll("&", "§"));
                return null;
            }
        };
        new PAction("command") {
            @Override
            public Object run(Player player, String... args) {
                String way = args[0];
                String command = "";
                for (int a = 1; a < args.length; a++) {
                    command += (args[a] + " ");
                }
                if (way.equalsIgnoreCase("player")) {
                    Bukkit.dispatchCommand(player, command.replaceAll("<PLAYER>", player.getName()));
                }
                if (way.equalsIgnoreCase("op")) {
                    if (!player.isOp()) {
                        player.setOp(true);
                        Bukkit.dispatchCommand(player, command.replaceAll("<PLAYER>", player.getName()));
                        player.setOp(false);
                    } else {
                        Bukkit.dispatchCommand(player, command.replaceAll("<PLAYER>", player.getName()));
                    }
                }
                if (way.equalsIgnoreCase("console")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("<PLAYER>", player.getName()));
                }
                return null;
            }
        };
    }
}

