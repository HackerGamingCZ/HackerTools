package cz.HackerGamingCZ.HackerTools.gui;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface GUI {

    default void register(){
        HackerTools.getPlugin().getGuiManager().addGUI(this);
    }

    default Inventory getInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        for(Item item : getItems(player)){
            inv.setItem(item.getPosition(), item.getIs());
        }
        return inv;
    }

    int getInventorySize();
    String getInventoryName();

    ArrayList<Item> getItems(Player player);

    default Item getItemByISName(ItemStack is, Player player){
        if(!is.getItemMeta().hasDisplayName()){
            return null;
        }
        for(Item item : getItems(player)){
            if(item.getIs().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName())){
                return item;
            }
        }
        return null;
    }

    default void open(Player player){
        player.openInventory(getInventory(player));
    }

}
