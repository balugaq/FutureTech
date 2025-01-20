package net.bxx2004.pandalib.bukkit.network;

import net.bxx2004.pandalib.bukkit.listener.event.PandaLibExtendEvent;

public class MessageEvent extends PandaLibExtendEvent {
    private MessageBox box;

    public MessageEvent(MessageBox box) {
        this.box = box;
    }

    public MessageBox getBox() {
        return box;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean bl) {

    }
}
