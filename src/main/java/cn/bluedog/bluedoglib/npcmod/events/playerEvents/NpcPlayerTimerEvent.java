package cn.bluedog.bluedoglib.npcmod.events.playerEvents;

import cn.bluedog.bluedoglib.Main;
import noppes.npcs.api.entity.IPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import site.liangbai.forgeeventbridge.event.EventHolder;
import site.liangbai.forgeeventbridge.wrapper.EventWrapper;

import java.lang.reflect.InvocationTargetException;

public class NpcPlayerTimerEvent extends NpcPlayerEvent implements EventHolder<EventWrapper.EventObject> {
    int id;
    public NpcPlayerTimerEvent(IPlayer player, int id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        super(player);
        this.id=id;
    }
    public NpcPlayerTimerEvent(){};
    public int getId() {
        return id;
    }

    @Override
    public void handle(EventWrapper<EventWrapper.EventObject> eventWrapper) {
        try {
            Bukkit.getServer().getPluginManager().callEvent(new NpcPlayerTimerEvent(eventWrapper.get("player",IPlayer.class),eventWrapper.get("id",Integer.class)));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
