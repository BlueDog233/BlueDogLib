package cn.bluedog.bluedoglib.commands;

import cn.bluedog.bluedoglib.npcmod.Kit;
import noppes.npcs.controllers.BankController;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(s.equalsIgnoreCase("bdl")&&commandSender.isOp()){
            if(strings[0].equalsIgnoreCase("kit")){
                if(strings[1].equalsIgnoreCase("create")){
                    List<ItemStack> items= Arrays.asList(((Player)commandSender).getInventory().getContents());;
                    List<net.minecraft.server.v1_16_R3.ItemStack> it=new ArrayList<>();
                    items.forEach(x->{
                        it.add(CraftItemStack.asNMSCopy(x));
                    });

                    Kit kit=new Kit(it);
                }else if(strings[1].equalsIgnoreCase("get")){
                    System.out.println(BankController.getInstance().banks.keySet());
                    if(strings[2]!=null){
                        System.out.println(BankController.getInstance().banks.get(Integer.valueOf(strings[2])).currencyInventory.stackLimit);
                    }
                }
            }
            return true;
        }
        return false;
    }
}
