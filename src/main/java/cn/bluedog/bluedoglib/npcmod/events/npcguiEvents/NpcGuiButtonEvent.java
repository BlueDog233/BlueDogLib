package cn.bluedog.bluedoglib.npcmod.events.npcguiEvents;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.ICustomGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import site.liangbai.forgeeventbridge.event.EventHolder;
import site.liangbai.forgeeventbridge.wrapper.EventWrapper;

import java.lang.reflect.InvocationTargetException;

public class NpcGuiButtonEvent extends GuiEvent implements EventHolder<EventWrapper.EventObject> {
    int buttonId;
    public NpcGuiButtonEvent(IPlayer player, ICustomGui gui, int buttonId) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        super(player, gui);
        this.buttonId=buttonId;
    }
    public NpcGuiButtonEvent(){};
    public int getButtonId() {
        return buttonId;
    }
    @Override
    public void handle(EventWrapper<EventWrapper.EventObject> eventWrapper) {
        IPlayer player=eventWrapper.get("player",IPlayer.class);
        ICustomGui gui=eventWrapper.get("gui",ICustomGui.class);
        try {
            Bukkit.getServer().getPluginManager().callEvent(new NpcGuiButtonEvent(player,gui,eventWrapper.get("buttonId",Integer.class)));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
