package net.bxx2004.pandalib.bukkit.language.component;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ActionChatText extends ChatText {
    private TextComponent text;
    private String data;

    public ActionChatText(String data) {
        super("data=" + data.replaceAll("<space>", " "));
        this.text = new TextComponent();
        text.setText(translate(data));
        this.data = data;
    }

    @Override
    public void send(CommandSender... target) {
        for (CommandSender commandSender : target) {
            commandSender.spigot().sendMessage(text);
        }
    }

    public ActionChatText setOrigin(TextComponent text) {
        this.text = text;
        return this;
    }

    public ActionChatText copy() {
        TextComponent component = new TextComponent(origin());
        List<BaseComponent> ex = new ArrayList<>();
        for (BaseComponent baseComponent : origin().getExtra()) {
            ex.add(baseComponent.duplicate());
        }
        component.setExtra(ex);
        return new ActionChatText(component.getText()).setOrigin(component);
    }

    public String getData() {
        return data;
    }

    public TextComponent origin() {
        return text;
    }

    public ActionChatText changeText(String data) {
        this.data = data;
        text.setText(translate(data));
        return this;
    }

    public ActionChatText append(ActionChatText text) {
        this.text.addExtra(text.origin());
        return this;
    }

    public ActionChatText append(String text) {
        this.text.addExtra(text);
        return this;
    }

    public ActionChatText writeClick(ClickEvent.Action action, String value) {
        this.text.setClickEvent(new ClickEvent(action, value));
        return this;
    }
}
