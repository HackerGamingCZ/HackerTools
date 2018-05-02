package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener{

    @EventHandler
    public void onItemClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();
        GUI gui = HackerTools.getPlugin().getGUIAPI().getGUI(inv.getTitle());
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
            return;
        }
        ItemStack item = e.getCurrentItem();
        Event event = gui.getItemsEvent(item);
        if(event == null){
            return;
        }
        event.getAction().cast(player);
        e.setCancelled(event.isCanceled());
        if(event.isClosingInventory()){
            player.closeInventory();
        }
    }

}
