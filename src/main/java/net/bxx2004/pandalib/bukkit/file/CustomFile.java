package net.bxx2004.pandalib.bukkit.file;

import java.io.File;
import java.io.IOException;

/**
 * 操控文件的接口
 */
public interface CustomFile {
    /**
     * 创建文件夹
     *
     * @param path 路径
     */
    public static void createPath(String path) {
        File file = new File(path);
        file.mkdirs();
    }

    /**
     * 创建文件
     *
     * @param path 路径
     */
    public static void createFile(String path) {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取这个文件类型
     *
     * @return 文件类型
     */
    public FileType getType();
}
