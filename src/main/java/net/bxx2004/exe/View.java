package net.bxx2004.exe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.jar.JarFile;

/**
 * 预定义配置文件视图
 */
public class View {
    public static void main(String[] args) {
        if (!getPath().endsWith("plugins")) {
            new ErrorView("此处无法打开,请将放入服务器插件目录重新尝试！");
        }
        try {
            JarFile file = new JarFile(System.getProperty("java.class.path"));
            if (file.getEntry("config.yml") == null) {
                new ErrorView("这不是一个有效的插件,无法打开其配置文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveConfig();
    }

    private static void saveConfig() {
        try {
            URL url = View.class.getClassLoader().getResource("config.yml");
            if (url == null) {
            } else {
                URLConnection connection = url.openConnection();
                connection.setUseCaches(false);
                InputStream stream = connection.getInputStream();
                try {
                    InputStreamReader input = new InputStreamReader(stream, "UTF-8");
                    File file = new File(getPath().replaceAll("%20", " ") + "/config.yml");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), true);
                    BufferedReader reder = new BufferedReader(input);

                    Iterator<String> i = reder.lines().iterator();
                    while (i.hasNext()) {
                        writer.println(i.next());
                    }
                    input.close();
                    writer.close();
                    reder.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } catch (IOException var4) {

        }
    }

    private static String getPath() {
        String path = View.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }
}
