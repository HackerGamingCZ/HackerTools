package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.events.Event;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {

    @EventHandler
    public void onItemClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();
        GUI gui = HackerTools.getPlugin().getGuiManager().getGUI(inv.getName());
        if(gui == null){
            return;
        }
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        Item item = gui.getItemByISName(e.getCurrentItem(), player);
        if(item == null){
            return;
        }
        Event event = item.getEvent();
        if(event == null){
            return;
        }
        if(!(event instanceof ItemInInventoryClickEvent)){
            return;
        }
        event.getAction().cast(player);
        e.setCancelled(item.isCanceled());
        if(item.isClosingInventory()){
            player.closeInventory();
        }
    }

}
