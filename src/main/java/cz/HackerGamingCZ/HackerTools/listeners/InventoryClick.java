package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
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
        Action action = item.getAction();
        if(action == null){
            return;
        }
        action.cast(player);
        e.setCancelled(item.isCanceled());
        if(item.isClosingInventory()){
            player.closeInventory();
        }
    }

}
