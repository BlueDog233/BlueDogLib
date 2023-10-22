package cn.bluedog.bluedoglib.configAPI.configs.datas;

import cn.bluedog.bluedoglib.configAPI.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataContainer {
    private List<Data> datas=new ArrayList<>();

    public List<Data> getDatas() {
        return datas;
    }

    public Data createNew(String name) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        cn.bluedog.bluedoglib.configAPI.reflect.DataContainer anno=this.getClass().getAnnotation(cn.bluedog.bluedoglib.configAPI.reflect.DataContainer.class);
        Plugin plugin= Bukkit.getServer().getPluginManager().getPlugin(anno.pluginName());
        String upperName= anno.name();
        File file=new File(plugin.getDataFolder().getPath()+"/"+upperName,name+".yml");
        if(!file.exists()){
            file.createNewFile();
        }
        YamlConfiguration yaml=YamlConfiguration.loadConfiguration(file);
        Data d=(Data) ConfigManager.getInstance().getDatas().get(this.getClass()).getConstructor(YamlConfiguration.class,String.class).newInstance(yaml,name);
        d.setFile(file);
        datas.add(d);
        return d;
    }
    public void save() throws IOException {
        for (Data data : datas) {
            data.save();
        }
    }
}

