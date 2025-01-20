package net.bxx2004.pandalib.bukkit.file;

import net.bxx2004.pandalib.bukkit.item.PItemStack;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Yaml文件工具
 */
public class PYml implements CustomFile {
    private File file;
    private FileConfiguration yaml;

    /**
     * 快速构建一个Yaml文件类型
     *
     * @param url 文件地址
     * @param b   创建新文件
     */
    public PYml(String url, boolean b) {
        this.file = new File(url);
        if (!file.exists()) {
            if (b) {
                try {
                    file.createNewFile();
                } catch (IOException ioException) {

                }
            }
        }
        this.yaml = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public FileType getType() {
        return FileType.YAML;
    }

    public String getName() {
        return file.getName();
    }

    /**
     * 返回一个 Configuration
     *
     * @return Configuration
     */
    public Configuration getYaml() {
        return this.yaml;
    }

    /**
     * 重载这个文件
     */
    public void reloadYaml() {
        this.yaml = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * 保存这个文件
     */
    public void saveYaml() {
        try {
            this.yaml.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * 为这个Yaml set值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        this.yaml.set(key, value);
        this.saveYaml();
        this.reloadYaml();
    }

    /**
     * 为这个Yaml get值
     *
     * @param key 键
     * @return Object
     */
    public Object get(String key) {
        this.reloadYaml();
        return this.yaml.get(key);
    }

    /**
     * 获取String类型值
     *
     * @param key 键
     * @return String类型值
     */
    public String getString(String key) {
        return (String) get(key);
    }

    public PItemStack getItem(String key) {
        if (yaml.getItemStack(key) != null) {
            return new PItemStack(yaml.getItemStack(key));
        }
        return null;
    }

    /**
     * 获取Boolean类型值
     *
     * @param key 键
     * @return Boolean类型值
     */
    public boolean getBoolean(String key) {
        return (boolean) get(key);
    }

    /**
     * 获取int类型值
     *
     * @param key 键
     * @return int类型值
     */
    public int getInt(String key) {
        return (int) get(key);
    }

    /**
     * 获取double类型值
     *
     * @param key 键
     * @return double类型值
     */
    public double getDouble(String key) {
        return (double) get(key);
    }

    /**
     * 获取List类型值
     *
     * @param key 键
     * @return List类型值
     */
    public List getList(String key) {
        return (List) get(key);
    }

    public Set<String> keys(String predefine) {
        return yaml.getConfigurationSection(predefine).getKeys(false);
    }

    public Set<String> keys() {
        return yaml.getKeys(false);
    }
}
