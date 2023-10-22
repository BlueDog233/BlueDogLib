package cn.bluedog.bluedoglib.configAPI.configs;

import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Config {


    private YamlConfiguration yaml;
    public Config(){

    }
    public YamlConfiguration getYaml() {
        return yaml;
    }
    public abstract void load();
}
