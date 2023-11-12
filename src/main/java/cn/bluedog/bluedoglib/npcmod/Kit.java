package cn.bluedog.bluedoglib.npcmod;


import net.minecraft.server.v1_16_R3.ItemStack;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.data.Bank;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Kit {
    Bank bank;
    public Kit(List<ItemStack> is){


            bank=new Bank();
            is.forEach((e)->{
                try {
                    Object obj=bank.getClass().getDeclaredField("currencyInventory").get(bank);
                    Method method=bank.getClass().getDeclaredField("currencyInventory").getClass().getMethod("addItemStack", net.minecraft.server.v1_16_R3.ItemStack.class);
                    System.out.println(method.invoke(obj,e));
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException(ex);
                }
            });
            BankController.getInstance().saveBank(bank);


    }
    public Kit(int id){
        if((bank=BankController.getInstance().getBank(id))!=null){
            bank=BankController.getInstance().getBank(id);
        }
    }


    public Bank getBank(){
        return bank;
    }
}
