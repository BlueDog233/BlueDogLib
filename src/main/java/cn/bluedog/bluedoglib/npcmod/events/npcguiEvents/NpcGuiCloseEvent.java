package cn.bluedog.bluedoglib.npcmod.events.npcguiEvents;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.ICustomGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import site.liangbai.forgeeventbridge.event.EventHolder;
import site.liangbai.forgeeventbridge.wrapper.EventWrapper;

import java.lang.reflect.InvocationTargetException;

public class NpcGuiCloseEvent extends GuiEvent implements EventHolder<EventWrapper.EventObject> {

    public NpcGuiCloseEvent(IPlayer player, ICustomGui gui) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        super(player, gui);
    }

    @Override
    public void handle(EventWrapper<EventWrapper.EventObject> eventWrapper) {
        IPlayer player=eventWrapper.get("player",IPlayer.class);
        ICustomGui gui=eventWrapper.get("gui",ICustomGui.class);
        try {
            Bukkit.getServer().getPluginManager().callEvent(new NpcGuiCloseEvent(player,gui));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public NpcGuiCloseEvent(){};
}
