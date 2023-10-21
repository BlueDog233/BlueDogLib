package cn.bluedog.bluedoglib.yamlAPI.configfile;

import cn.bluedog.bluedoglib.yamlAPI.YamlManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public abstract class Data {
    private File file=null;
    private Plugin plugin=null;
    private  String name;


    private YamlConfiguration yaml=null;
    private String upperName;


    public File getFile() {
        return file;
    }

    public String getUpperName() {
        return upperName;
    }

    Data(Plugin plugin, String upperName, String name) throws IOException {
        this.plugin=plugin;
        this.name=name;
        file=new File(plugin.getDataFolder(),upperName+"/"+name+".yml");
        yaml=YamlConfiguration.loadConfiguration(file);
        if(!file.exists()){
            file.createNewFile();
        }else{
            load();
        }
        YamlManager.getInstance().getDatas().add(this);

    }
    public YamlConfiguration getYaml() {
        return yaml;
    }


    public abstract void load();

    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }
    public void save(){
        plugin.saveResource(upperName+"/"+name+".yml",false);

    }

}
