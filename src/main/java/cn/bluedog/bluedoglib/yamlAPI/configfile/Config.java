package cn.bluedog.bluedoglib.yamlAPI.configfile;

import cn.bluedog.bluedoglib.yamlAPI.YamlManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

public abstract class Config {
    private File file=null;
    private Plugin plugin=null;
    private  String name;

    public YamlConfiguration getYaml() {
        return yaml;
    }

    private YamlConfiguration yaml=null;



    Config(Plugin plugin, String name){
        this.plugin=plugin;
        this.name=name;
        file=new File(plugin.getDataFolder(),name+".yml");
        YamlManager.getInstance().getConfigs().add(this);
        load();
    }



    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }

    public void reload(){
        load();
    }



    public void load(){
        create();
        yaml=YamlConfiguration.loadConfiguration(file);
        loadConfig();
    }
    public void create(){
        if(!file.exists()){
            plugin.saveResource(name+".yml",false);
        }
    }
    public abstract void loadConfig();

}
