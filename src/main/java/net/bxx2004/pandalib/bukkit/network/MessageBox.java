package net.bxx2004.pandalib.bukkit.network;

import java.util.function.Consumer;

public class MessageBox {
    private String header;
    private String content;

    public MessageBox(String header) {
        this.header = header;
    }

    public MessageBox(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public void append(String id, String data) {
        content = content + id + "=" + data + ";";
    }

    public String getHeader() {
        return header;
    }

    public String getNode(String id) {
        for (String a : content.split(";")) {
            String b = a.split("=")[0];
            String c = a.split("=")[1];
            if (b.equals(id)) {
                return c;
            }
        }
        return null;
    }

    public byte[] origin() {
        return content.getBytes();
    }

    public void read(Consumer<String> data) {
        for (String a : content.split(";")) {
            data.accept(a);
        }
    }
}
