package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.InteractableItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDrop implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        ItemStack is = e.getItemDrop().getItemStack();
        if(is == null || is.getType() == Material.AIR){
            return;
        }
        InteractableItem item = HackerTools.getPlugin().getItemInteractManager().getItemByIs(is);
        if(item == null){
            return;
        }
        e.setCancelled(!item.isDrop());
    }

}
