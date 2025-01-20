package net.bxx2004.pandalibloader;

import java.io.InputStream;

/**
 * 代表插件
 */
public interface PandaLibPlugin<T> {
    /**
     * 插件
     *
     * @return 插件
     */
    public T getPlugin();

    /**
     * 获取当前文件所在路径
     *
     * @return 所在路径
     */
    public String getPath();

    /**
     * 获取当前插件内文件
     *
     * @param name 路径
     * @return InputStream
     */
    public InputStream getJarFile(String name);
}
