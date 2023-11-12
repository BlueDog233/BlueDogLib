package cn.bluedog.bluedoglib.npcmod.events.playerEvents;

import noppes.npcs.api.entity.IPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import site.liangbai.forgeeventbridge.event.EventHolder;
import site.liangbai.forgeeventbridge.wrapper.EventWrapper;

import java.lang.reflect.InvocationTargetException;

public class NpcPlayerKeyReleasedEvent extends NpcPlayerEvent implements EventHolder<EventWrapper.EventObject> {
    int keyId;
    public NpcPlayerKeyReleasedEvent(IPlayer player, int keyId) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        super(player);
        this.keyId=keyId;
    }
    public NpcPlayerKeyReleasedEvent(){};
    public int getKeyId() {
        return keyId;
    }
    @Override
    public void handle(EventWrapper<EventWrapper.EventObject> eventWrapper) {
        try {
            Bukkit.getServer().getPluginManager().callEvent(new NpcPlayerKeyReleasedEvent(eventWrapper.get("player",IPlayer.class),eventWrapper.get("key",Integer.class)));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
