package cn.bluedog.bluedoglib;

import cn.bluedog.bluedoglib.commands.Command;
import cn.bluedog.bluedoglib.configAPI.ConfigManager;
import cn.bluedog.bluedoglib.configAPI.reflect.Loader;
import cn.bluedog.bluedoglib.npcmod.events.npcguiEvents.NpcGuiButtonEvent;
import cn.bluedog.bluedoglib.npcmod.events.npcguiEvents.NpcGuiCloseEvent;
import cn.bluedog.bluedoglib.npcmod.events.playerEvents.NpcPlayerKeyPressedEvent;
import cn.bluedog.bluedoglib.npcmod.events.playerEvents.NpcPlayerKeyReleasedEvent;
import cn.bluedog.bluedoglib.npcmod.events.playerEvents.NpcPlayerTimerEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import site.liangbai.forgeeventbridge.event.CustomEventBusProxy;
import site.liangbai.forgeeventbridge.event.EventBridge;

import java.lang.reflect.InvocationTargetException;

public final class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {

        // Plugin startup logic
        System.out.println("蓝泉插件载入");
        getCommand("bld").setExecutor(new Command());
        try {
            new NpcGuiCloseEvent().register(registe("noppes.npcs.api.event.CustomGuiEvent$CloseEvent"));
            new NpcGuiButtonEvent().register(registe("noppes.npcs.api.event.CustomGuiEvent$ButtonEvent"));
            new NpcPlayerTimerEvent().register(registe("noppes.npcs.api.event.PlayerEvent$TimerEvent"));
            new NpcPlayerKeyPressedEvent().register(registe("noppes.npcs.api.event.PlayerEvent$KeyPressedEvent"));
            new NpcPlayerKeyReleasedEvent().register(registe("noppes.npcs.api.event.PlayerEvent$KeyReleasedEvent"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static EventBridge registe(String str) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class c=Class.forName("noppes.npcs.api.wrapper.WrapperNpcAPI");

       return  EventBridge.builder().source(str).bus(new CustomEventBusProxy(c.getDeclaredField("EVENT_BUS").get(null))).build();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
