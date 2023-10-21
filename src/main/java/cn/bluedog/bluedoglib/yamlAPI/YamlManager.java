package cn.bluedog.bluedoglib.yamlAPI;

import cn.bluedog.bluedoglib.yamlAPI.configfile.Config;
import cn.bluedog.bluedoglib.yamlAPI.configfile.Data;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class YamlManager {
    private static YamlManager yamlManager;
    private List<Config> configs=new ArrayList<>();
    private List<Data> datas=new ArrayList<>();
    private  List<Class> configClass=new ArrayList<>();
    private  List<Class> dataClass=new ArrayList<>();

    public List<Data> getDatas() {
        return datas;
    }

    public List<Config> getConfigs() {
        return configs;
    }


    private YamlManager(){

    }
    public void reload(Plugin plugin){
        for (Config config : configs) {
            if(config.getPlugin().getName().equalsIgnoreCase(plugin.getName())){
                config.reload();
            }
        }
    }

    public List<Class> getConfigClass() {
        return configClass;
    }

    public List<Class> getDataClass() {
        return dataClass;
    }

    public void register(Class<?> clz){
        if(clz.isAssignableFrom(Data.class)){
            dataClass.add(clz);
        }
        if(clz.isAssignableFrom(Config.class)){
            configClass.add(clz);
        }
    }


    public Config getConfig(Plugin plugin,String name){
        for (Config config : configs) {
            if(config.getPlugin().getName().equalsIgnoreCase(plugin.getName()) && config.getName().equals(name)){
                return config;
            }
        }

        return null;
    }
    public Data getData(Plugin plugin,String upper,String name){
        for(Data data:datas){
            if(data.getUpperName().equals(upper) && data.getName().equals(name)){
                return data;
            }
        }
        return null;
    }
    public List<Data> getDatas(Plugin plugin,String upper){
        List<Data> d=new ArrayList<>();
        for(Data data:datas){
            if(data.getUpperName().equals(upper)){
                d.add(data);
            }

        }
        return d;
    }
    public static YamlManager getInstance(){
        if(yamlManager==null){
            yamlManager=new YamlManager();
        }
        return yamlManager;
    }



}
