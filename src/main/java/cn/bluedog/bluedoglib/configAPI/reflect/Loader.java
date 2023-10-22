package cn.bluedog.bluedoglib.configAPI.reflect;

import cn.bluedog.bluedoglib.configAPI.ConfigManager;

import cn.bluedog.bluedoglib.configAPI.configs.datas.Data;
import cn.bluedog.bluedoglib.configAPI.configs.datas.DataContainer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Loader {
    public static void load() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //Config加载器
        for (Class config : ConfigManager.getInstance().getConfigs()) {
            Config ap= (Config) config.getAnnotation(Config.class);
            String name=ap.name();
            Plugin plugin= Bukkit.getServer().getPluginManager().getPlugin(ap.pluginName());
            File file=new File(plugin.getDataFolder(),name+".yml");
            if(!file.exists()){
                plugin.saveResource(name+".yml",false);
            }
            //给Config对象注入Yaml属性;
            YamlConfiguration yaml=YamlConfiguration.loadConfiguration(file);
            cn.bluedog.bluedoglib.configAPI.configs.Config c=(cn.bluedog.bluedoglib.configAPI.configs.Config)config.getConstructor().newInstance();
            Field field=config.getDeclaredField("yaml");
            field.setAccessible(true);
            field.set(c,yaml);
            //加入yamlConfig列表,并加载
            ConfigManager.getInstance().getYamlConfigs().add(c);
            c.load();
        }
        //DataContainer加载器
        for (Class Datass : ConfigManager.getInstance().getDatas().keySet()) {
            cn.bluedog.bluedoglib.configAPI.reflect.DataContainer dataContainer= (cn.bluedog.bluedoglib.configAPI.reflect.DataContainer) Datass.getAnnotation(cn.bluedog.bluedoglib.configAPI.reflect.DataContainer.class);
            String upperName= dataContainer.name();
            Plugin plugin= Bukkit.getServer().getPluginManager().getPlugin(dataContainer.pluginName());
            File file=new File(plugin.getDataFolder().getPath()+"/"+upperName+"/");
            file.mkdir();
            DataContainer dataContainer1=(DataContainer) dataContainer.getClass().getConstructor().newInstance();

            if(file.isDirectory()){
                //datas加载
                File[] datas=file.listFiles();
                for (File data : datas) {
                    YamlConfiguration yaml=YamlConfiguration.loadConfiguration(data);
                    Data d= (Data) ConfigManager.getInstance().getDatas().get(Datass).getConstructor(YamlConfiguration.class,String.class).newInstance(yaml,data.getName().replace(".yml",""));
                    d.load();
                    d.setFile(data);
                    dataContainer1.getDatas().add(d);
                }
            }
            ConfigManager.getInstance().getYamlDatass().add(dataContainer1);

        }
    }

    public static void unload(){
        //清空列表
        ConfigManager.getInstance().getYamlDatass().clear();
        ConfigManager.getInstance().getYamlConfigs().clear();
        ConfigManager.getInstance().getYamlDatass().clear();


    }
}
