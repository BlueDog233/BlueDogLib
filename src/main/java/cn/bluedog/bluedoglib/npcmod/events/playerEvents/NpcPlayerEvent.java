package cn.bluedog.bluedoglib.npcmod.events.playerEvents;

import cn.bluedog.bluedoglib.npcmod.NPCUtils;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.lang.reflect.InvocationTargetException;

public class NpcPlayerEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private IPlayer player;

    public NpcPlayerEvent(IPlayer player) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        this.player = player;
    }
    public NpcPlayerEvent(){

    }
    public IPlayer getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
