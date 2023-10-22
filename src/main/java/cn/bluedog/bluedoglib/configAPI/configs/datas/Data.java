package cn.bluedog.bluedoglib.configAPI.configs.datas;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Data {
    YamlConfiguration yamlConfiguration;
    private String name;

    public void setFile(File file) {
        this.file = file;
    }

    public abstract void save();
    private File file;
    public Data(YamlConfiguration yamlConfiguration, String name) {
        this.yamlConfiguration = yamlConfiguration;
        this.name = name;
    }

    public abstract void load();

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

    public String getName() {
        return name;
    }
}
