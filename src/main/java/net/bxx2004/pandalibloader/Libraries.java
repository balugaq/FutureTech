package net.bxx2004.pandalibloader;

import java.util.HashMap;

/**
 * 插件前置库加载
 */
public abstract class Libraries {
    public HashMap<String, String> url = new HashMap<String, String>();

    /**
     * 添加依赖
     *
     * @param local    本地路径 , server/libs/{local}
     * @param internet 网络路径
     */
    public void addURL(String local, String internet) {
        url.put(local, internet);
    }
}
