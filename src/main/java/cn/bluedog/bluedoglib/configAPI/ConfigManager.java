package cn.bluedog.bluedoglib.configAPI;

import cn.bluedog.bluedoglib.configAPI.configs.Config;
import cn.bluedog.bluedoglib.configAPI.configs.datas.DataContainer;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {
    private static ConfigManager manager;
    private List<Class> configs=new ArrayList<>();
    private HashMap<Class,Class> datas=new HashMap<>();

    private List<Config> yamlConfigs=new ArrayList<>();
    private List<DataContainer> yamlDatass=new ArrayList<>();

    public List<Config> getYamlConfigs() {
        return yamlConfigs;
    }

    public List<DataContainer> getYamlDatass() {
        return yamlDatass;
    }

    public List<Class> getConfigs() {
        return configs;
    }

    public HashMap<Class, Class> getDatas() {
        return datas;
    }

    private  ConfigManager(){
    }
    public static ConfigManager getInstance(){
        if(manager==null){
            manager=new ConfigManager();
        }
        return manager;
    }

    public void register(Class data){
        if(data.isAssignableFrom(Config.class)){
            configs.add(data);
        }
    }
    public void register(Class dataContainer,Class data){
        datas.put(dataContainer,data);
    }
    public Config getConfig(Plugin plugin, String name){
        for (Config yamlConfig : yamlConfigs) {
            cn.bluedog.bluedoglib.configAPI.reflect.Config clz=yamlConfig.getClass().getAnnotation(cn.bluedog.bluedoglib.configAPI.reflect.Config.class);
            if(clz.pluginName().equals(plugin.getName())&&clz.name().equals(name)){
                return yamlConfig;
            }
        }
        return null;
    }
    public DataContainer getContainer(Plugin plugin, String name){
        for (DataContainer yamlData : yamlDatass) {
            cn.bluedog.bluedoglib.configAPI.reflect.DataContainer clz=yamlData.getClass().getAnnotation(cn.bluedog.bluedoglib.configAPI.reflect.DataContainer.class);
            if(clz.pluginName().equals(plugin.getName())&&clz.name().equals(name)){
                return yamlData;
            }
        }
        return null;
    }

}
