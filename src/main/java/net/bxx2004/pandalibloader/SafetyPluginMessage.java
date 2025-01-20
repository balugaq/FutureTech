package net.bxx2004.pandalibloader;

import java.net.URL;

/**
 * 插件验证信息
 */
public class SafetyPluginMessage<T> {
    private URL url;
    private T pandaLibPlugin;

    /**
     * 构造函数
     *
     * @param url    路径 .txt
     * @param plugin 插件
     */
    public SafetyPluginMessage(URL url, T plugin) {
        this.url = url;
        this.pandaLibPlugin = plugin;
    }

    /**
     * 返回路径
     *
     * @return 路径
     */
    public URL getUrl() {
        return url;
    }

    /**
     * 返回插件
     *
     * @return 插件
     */
    public T getPandaLibPlugin() {
        return pandaLibPlugin;
    }
}
