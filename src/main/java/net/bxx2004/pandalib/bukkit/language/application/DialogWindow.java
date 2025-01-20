package net.bxx2004.pandalib.bukkit.language.application;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.bxx2004.pandalib.PandaLib;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.language.application.image.ImageChar;
import net.bxx2004.pandalib.bukkit.language.application.image.ImageMessage;
import net.bxx2004.pandalib.bukkit.language.component.ActionBar;
import net.bxx2004.pandalib.bukkit.language.component.ActionChatText;
import net.bxx2004.pandalib.bukkit.language.component.ChatText;
import net.bxx2004.pandalib.bukkit.listener.PListener;
import net.bxx2004.pandalib.bukkit.util.PMath;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.bxx2004.pandalib.bukkit.language.Text.translate;

public class DialogWindow {
    private String sender = "";
    private UUID uuid = UUID.randomUUID();
    private String description;
    private ActionChatText[] answers;
    private int lock = 0;
    private List<String> players;
    private Temple temple;
    private String content_clone;
    private HashMap<Integer, List<BaseComponent>> ec_cache = new HashMap<>();
    private HashMap<Integer, String> change_cache = new HashMap<>();

    private Consumer<CommandSender> start = sender -> {

    };
    private Consumer<CommandSender> end = sender -> {

    };

    public DialogWindow(String description, ActionChatText... answers) {
        this.players = new ArrayList<>();
        this.description = description;
        this.answers = answers;
        int i = 0;
        if (answers.length > 0) {
            for (ActionChatText answer : answers) {
                this.answers[i] = answer.writeClick(ClickEvent.Action.RUN_COMMAND, "/dialogwindow " + uuid + " " + i);
                i++;
            }
            for (int o = 0; o < answers.length; o++) {
                String a = answers[o].origin().getText();
                change_cache.put(o, a);
            }
        }
        content_clone = sender + description;
    }

    public DialogWindow(String sender, String description, ActionChatText... answers) {
        this(description, answers);
        this.sender = sender;
    }

    public void applyTemple(Temple temple) {
        this.temple = temple;
        this.sender = temple.sender.replaceAll("@dialogSender", sender);
        this.description = temple.description.replaceAll("@dialogDescription", description);
        content_clone = sender + description;
        if (answers != null && answers.length > 0) {
            if (temple.answer.contains("@dialogAnswer[all")) {
                ActionChatText[] texts = new ActionChatText[answers.length];
                int i = 0;
                for (ActionChatText answer : answers) {
                    ActionChatText cache = new ActionChatText("");
                    String o = temple.answer.replaceAll("@dialogAnswer\\[", "").replaceAll("]", "");
                    cache.changeText(o.split(",")[1].split("=")[1].equals("null") ? "" : o.split(",")[1].split("=")[1]);
                    cache.append(answer).append(o.split(",")[2].split("=")[1].equals("null") ? "" : o.split(",")[2].split("=")[1]);
                    texts[i] = cache;
                    i++;
                }
                answers = texts;
            } else {
                //@DialogAnswer[pre=&f-> ,end=\n]
                ActionChatText[] texts = new ActionChatText[answers.length];
                final int[] i = {0};
                PMath.regx("\\[(.*?)]", temple.answer, c -> {
                    ActionChatText cache = new ActionChatText("");
                    String o = c.replaceAll("\\[", "").replace("]", "");
                    cache.changeText(o.split(",")[0].split("=")[1].equals("null") ? "" : o.split(",")[0].split("=")[1]);
                    cache.append(answers[i[0]]).append(o.split(",")[1].split("=")[1].equals("null") ? "" : o.split(",")[1].split("=")[1]);
                    texts[i[0]] = cache;
                    i[0]++;
                });
                for (int ca = 0; ca < texts.length; ca++) {
                    answers[ca] = texts[ca];
                }
            }
            ec_cache.clear();
            change_cache.clear();
            for (int i = 0; i < answers.length; i++) {
                change_cache.put(i, answers[i].origin().getText());
                List<BaseComponent> a = answers[i].origin().getExtra();
                ec_cache.put(i, a);
            }
            //answers[answers.length - 1].changeText(answers[answers.length -1 ].origin().getText().substring(0,answers[answers.length -1 ].origin().getText().length()-1));
        }
    }

    public DialogWindow start(Consumer<CommandSender> c) {
        start = c;
        return this;
    }

    public DialogWindow end(Consumer<CommandSender> c) {
        end = c;
        return this;
    }

    public void send(CommandSender... commandSender) {
        String space = "";
        for (char c : sender.toCharArray()) {
            space += " ";
        }
        for (int i = 0; i < 20; i++) {
            ChatText a = new ChatText(" ");
            a.send(commandSender);
        }
        if (temple != null && temple.getLimitLine() > 1) {
            AnimationList list = new AnimationList(temple);
            String a = content_clone;
            for (char c : a.toCharArray()) {
                list.add(c);
            }
            description = list.get();
        }

        for (CommandSender commandSender1 : commandSender) {
            start.accept(commandSender1);
            if (!players.contains(commandSender1.getName())) {
                players.add(commandSender1.getName());
            }
            commandSender1.sendMessage(" ");
        }
        ActionChatText a;
        if (temple == null) {
            a = new ActionChatText(sender + "\n" + space + description);
        } else if (temple.getLimitLine() > 1) {
            a = new ActionChatText(description.replaceFirst("&", ""));
        } else {
            a = new ActionChatText(sender + description);
        }
        for (ActionChatText answer : answers) {
            a.append(answer);
        }
        a.send(commandSender);
        ActionBar bar = new ActionBar("&b按下 &e0-9 &b选择选项,按下 &eF &b确认选项");
        bar.send(commandSender);
    }

    private void sendLock(int slot) {
        this.lock = slot;
        for (int i = 0; i < answers.length; i++) {
            answers[i] = answers[i].changeText(change_cache.get(i));
            if (temple != null) {
                answers[i].origin().setExtra(ec_cache.get(i));
            }
        }
        List<BaseComponent> list = new ArrayList<>();
        if (temple == null) {
            answers[lock] = answers[lock].changeText("&b" + ChatColor.stripColor(answers[lock].origin().getText()));
        } else {
            answers[lock].changeText(ChatText.translate(temple.getChooseColor() + ChatColor.stripColor(answers[lock].origin().getText())));
            for (int i1 = 0; i1 < answers[lock].origin().getExtra().size(); i1++) {
                TextComponent components = new TextComponent();
                TextComponent a = (TextComponent) answers[lock].origin().getExtra().get(i1);
                components.setText(translate(temple.getChooseColor()) + ChatColor.stripColor(a.getText()));
                components.setClickEvent(a.getClickEvent());
                list.add(components);
            }
        }
        answers[lock].origin().setExtra(list);
    }

    public void sendAnimation(CommandSender... commandSender) {
        String space = "";
        for (int i = 0; i < sender.length(); i++) {
            space += " ";
        }
        for (CommandSender target : commandSender) {
            if (!players.contains(target.getName())) {
                players.add(target.getName());
            }
            start.accept(target);
            char[] content;
            if (temple == null) {
                content = translate(sender + "\n" + space + description).toCharArray();
            } else {
                content = translate(sender + description).toCharArray();
            }
            new BukkitRunnable() {
                int i = 0;
                String m = "";
                AnimationList list = new AnimationList(temple);

                @Override
                public void run() {
                    if (!players.contains(target.getName())) {
                        cancel();
                    }
                    m += content[i];
                    for (int i = 0; i < 100; i++) {
                        target.sendMessage(" ");
                    }
                    if (temple != null && temple.getLimitLine() > 1) {
                        list.add(content[i]);
                        ChatText chatText = new ChatText(list.get());
                        chatText.send(commandSender);
                    } else {
                        PMessage.to(target, m);
                    }
                    if (target instanceof Player) {
                        Player p = (Player) target;
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 3, 3);
                    }
                    i = i + 1;
                    if (i == content.length) {
                        for (int i = 0; i < 100; i++) {
                            target.sendMessage(" ");
                        }
                        send(target);
                        if (target instanceof Player) {
                            Player p = (Player) target;
                            p.playSound(p.getLocation(), Sound.UI_TOAST_OUT, 3, 3);
                        }
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, 2);
        }
        ActionBar bar = new ActionBar("&b按下 &e0-9 &b选择选项,按下 &eF &b确认选项");
        bar.send(commandSender);
    }

    public DialogWindow click(BiConsumer<UUID, Integer> consumer) {
        final String[] cuuid = new String[1];
        final int[] slot = new int[1];
        new PListener() {
            @EventHandler
            public void onCommand(PlayerCommandPreprocessEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    players.remove(e.getPlayer().getName());
                    end.accept(e.getPlayer());
                    if (e.getMessage().contains("dialogwindow")) {
                        e.setCancelled(true);
                        cuuid[0] = e.getMessage().split(" ")[1];
                        slot[0] = Integer.parseInt(e.getMessage().split(" ")[2]);
                        if (cuuid[0].equals(uuid.toString())) {
                            consumer.accept(uuid, slot[0]);
                        }
                    }
                }
                if (players.size() <= 0) {
                    unhook();
                }
            }

            @EventHandler
            public void onChangeSlot(PlayerItemHeldEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    if (e.getNewSlot() < answers.length) {
                        sendLock(e.getNewSlot());
                        for (int i = 0; i < 100; i++) {
                            e.getPlayer().sendMessage(" ");
                        }
                        send(e.getPlayer());
                    }
                }
                if (players.size() <= 0) {
                    unhook();
                }
            }
        }.hook(PandaLib.initPlugin.getName());
        new PListener() {
            @EventHandler
            public void onF(PlayerSwapHandItemsEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    e.getPlayer().chat("/dialogwindow " + uuid + " " + lock);
                    players.remove(e.getPlayer().getName());
                    end.accept(e.getPlayer());
                    unhook();
                }
            }
        }.hook(PandaLib.initPlugin.getName());
        new PListener() {
            @EventHandler
            public void onMove(PlayerMoveEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    e.setCancelled(true);
                }
            }
        }.hook(PandaLib.initPlugin.getName());
        new PListener() {
            @EventHandler
            public void onJump(PlayerJumpEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    players.remove(e.getPlayer().getName());
                    unhook();
                    end.accept(e.getPlayer());
                }
            }
        }.hook(PandaLib.initPlugin.getName());
        new PListener() {
            @EventHandler
            public void onQuit(PlayerJoinEvent e) {
                if (players.contains(e.getPlayer().getName())) {
                    players.remove(e.getPlayer().getName());
                    unhook();
                }
            }
        }.hook(PandaLib.initPlugin.getName());
        return this;
    }

    public static class Temples {
        public final static Temple TALK = new Temple(
                "&f&l@dialogSender:\n",
                "  &7“ @dialogDescription ”\n",
                "@dialogAnswer[all,pre=&f-> ,end=\n]"
        );

        public final static Temple AVATAR(UUID player, ImageChar imageChar) {
            Temple temple = new Temple("&9&l@dialogSender:\n",
                    "  &f@dialogDescription\n",
                    "@dialogAnswer[all,pre=null,end=     ]"
            ).setChooseColor("&7").setLimitLine(8).setLimitSize(25);
            URL url = null;
            try {
                URL a = new URL("https://minepic.org/avatar/8/" + player.toString().replaceAll("-", ""));
                ImageIO.read(a);
                url = a;
            } catch (IOException e) {
                try {
                    url = new URL("https://minepic.org/avatar/8/6ad0c11d62a546d4ae90ac5cff7ec11f");
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
            }
            ImageMessage m = ImageMessage.create(url, imageChar);
            temple.setLimitLinePrefix(PMath.toStringList(m.getLines()));
            return temple;
        }

    }

    private static class AnimationList {
        private List<String> prefix;
        private Temple temple;
        private List<String> content;

        public AnimationList(Temple temple) {
            if (temple != null) {
                this.prefix = temple.getLimitLinePrefix();
                this.temple = temple;
                this.content = new ArrayList<>();
            }
        }

        public void add(char c) {
            if (content.isEmpty()) {
                content.add(String.valueOf(c));
            }
            if (temple.limitLine <= content.size()) {
                return;
            }
            if (content.get(content.size() - 1).endsWith("\n") || content.get(content.size() - 1).length() > temple.limitSize) {
                content.add(String.valueOf(c));
            } else {
                String last = content.get(content.size() - 1);
                last += c;
                content.set(content.size() - 1, last);
            }
        }

        private String findLong() {
            int index;
            int size = 0;
            for (int i = 0; i < prefix.size(); i++) {
                if (prefix.get(i).length() > size) {
                    size = prefix.get(i).length();
                    index = i;
                }
            }
            String a = "";
            for (int i = 0; i < size; i++) {
                a += " ";
            }
            return a;
        }

        public String get() {
            List<String> cache = new ArrayList<>();
            if (prefix.size() > content.size()) {
                for (int i = 0; i < prefix.size(); i++) {
                    if (i > content.size() - 1) {
                        cache.add(prefix.get(i) + findLong());
                    } else {
                        cache.add(prefix.get(i) + content.get(i));
                    }
                }
            } else if (content.size() > prefix.size()) {
                for (int i = 0; i < content.size(); i++) {
                    if (i > prefix.size() - 1) {
                        cache.add(prefix.get(i) + findLong());
                    } else {
                        cache.add(prefix.get(i) + content.get(i));
                    }
                }
            } else {
                for (int i = 0; i < content.size(); i++) {
                    cache.add(prefix.get(i) + content.get(i));
                }
            }
            StringBuffer buffer = new StringBuffer();
            for (String s : cache) {
                s = s.replaceAll("\\n", "");
                buffer.append(s + "\n");
            }

            return buffer.substring(0, buffer.length() - 1);
        }
    }

    public static class Temple {
        //@dialogSender
        private String sender;
        //@DialogDescription
        private String description;
        //@DialogAnswer[all]
        private String answer;
        private String chooseColor = "&b";
        private int limitLine = -1;
        private List<String> limitLinePrefix = new ArrayList<>();
        private int limitSize = -1;

        public Temple(String sender, String description, String answer) {
            this.sender = sender;
            this.description = description;
            this.answer = answer;
        }

        public int getLimitSize() {
            return limitSize;
        }

        public Temple setLimitSize(int limitSize) {
            this.limitSize = limitSize;
            return this;
        }

        public String getChooseColor() {
            return chooseColor;
        }

        public Temple setChooseColor(String chooseColor) {
            this.chooseColor = chooseColor;
            return this;
        }

        public int getLimitLine() {
            return limitLine;
        }

        public Temple setLimitLine(int limitLine) {
            this.limitLine = limitLine;
            return this;
        }

        public List<String> getLimitLinePrefix() {
            return limitLinePrefix;
        }

        public Temple setLimitLinePrefix(List<String> limitLinePrefix) {
            this.limitLinePrefix = limitLinePrefix;
            return this;
        }

        public String getSender() {
            return sender;
        }

        public Temple setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Temple setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getAnswer() {
            return answer;
        }

        public Temple setAnswer(String answer) {
            this.answer = answer;
            return this;
        }
    }
}
