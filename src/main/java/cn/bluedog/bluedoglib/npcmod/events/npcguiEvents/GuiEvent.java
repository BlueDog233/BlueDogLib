package cn.bluedog.bluedoglib.npcmod.events.npcguiEvents;

import cn.bluedog.bluedoglib.npcmod.NPCUtils;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.ICustomGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.lang.reflect.InvocationTargetException;

public class GuiEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private IPlayer player;
    private ICustomGui gui;

    public GuiEvent(IPlayer player,ICustomGui gui) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        this.player = player;
        this.gui=gui;
    }

    public IPlayer getPlayer() {
        return player;
    }
    public GuiEvent(){};
    public ICustomGui getGui() {
        return gui;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
