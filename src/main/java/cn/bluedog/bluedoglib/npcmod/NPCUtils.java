package cn.bluedog.bluedoglib.npcmod;

import net.minecraft.client.gui.screen.NpcGuiHelper;
import noppes.npcs.NPCSpawning;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemArmor;
import noppes.npcs.api.item.IItemStack;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NPCUtils {
    public static IPlayer getPlayer(Player p) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        IEntity i=getEntity(p);
        return (IPlayer)i;
    }
    public static IEntity getEntity(Entity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m=NpcAPI.class.getMethod("getIEntity", net.minecraft.server.v1_16_R3.Entity.class);
        return (IEntity)(m.invoke(NpcAPI.Instance(),(Entity)((CraftEntity)entity).getHandle()));
    }
    public static IItemStack getItem(ItemStack itemStack) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method m=NpcAPI.class.getMethod("getIItemStack", net.minecraft.server.v1_16_R3.ItemStack.class);
        return (IItemStack) m.invoke(NpcAPI.Instance(),CraftItemStack.asNMSCopy(itemStack));
    }


}
