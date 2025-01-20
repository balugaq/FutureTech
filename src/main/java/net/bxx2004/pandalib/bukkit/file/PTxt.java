package net.bxx2004.pandalib.bukkit.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Txt文件工具
 */
public class PTxt implements CustomFile {
    private File file;
    private PrintWriter writer;
    private BufferedReader reader;
    private FileWriter fileWriter;

    public PTxt() {
    }

    /**
     * @param url 文件地址
     */
    public PTxt(String url) {
        this.file = new File(url);
        try {
            this.fileWriter = new FileWriter(this.file, true);
            this.writer = new PrintWriter(this.fileWriter, true);
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    @Override
    public FileType getType() {
        return FileType.TXT;
    }

    /**
     * 输出一些东西
     *
     * @param object 输出内容
     */
    public void println(Object object) {
        writer.println(object);
    }

    /**
     * 读一整行消息
     *
     * @param i 行数
     * @return 消息
     */
    public String read(int i) {
        for (int o = 0; o < i; o++) {
            try {
                reader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        try {
            String a = reader.readLine();
            return a;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    /**
     * 返回全部TXT内容
     *
     * @return 全部TXT内容
     */
    public Iterator iterator() {
        Iterator iterator = this.reader.lines().iterator();
        return iterator;
    }

    /**
     * 关闭流
     */
    public void close() {
        this.writer.close();
        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
