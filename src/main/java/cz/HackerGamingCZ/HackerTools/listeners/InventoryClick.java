package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.PlayerAction;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();
        GUI gui = HackerTools.getPlugin().getGuiManager().getGUI(inv.getName());
        if (gui == null) {
            return;
        }
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            return;
        }
        Item item = gui.getItemByISName(e.getCurrentItem(), htPlayer);
        if (item == null) {
            return;
        }
        PlayerAction action = item.getAction();
        if (action == null) {
            return;
        }
        action.execute(htPlayer);
        e.setCancelled(item.isCanceled());
        if (item.isClosingInventory()) {
            player.closeInventory();
        }
    }

}
