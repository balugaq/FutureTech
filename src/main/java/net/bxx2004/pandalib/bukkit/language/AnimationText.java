package net.bxx2004.pandalib.bukkit.language;

import org.bukkit.command.CommandSender;

public abstract class AnimationText extends Text {
    public abstract void send(CommandSender sender, AnimationTextType type);

    public abstract void send(AnimationTextType type, CommandSender... sender);

    public enum AnimationTextType {
        WRITE,
        FLOWER,
        ONE_BY_ONE
    }
}
