package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.InteractableItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Action action = e.getAction();
        if(action == Action.PHYSICAL){
            return;
        }
        Player player = e.getPlayer();
        ItemStack is = e.getItem();
        if(is == null){
            return;
        }
        if(!is.getItemMeta().hasDisplayName()){
            return;
        }
        InteractableItem item = HackerTools.getPlugin().getItemInteractAPI().getItemByIs(is);
        if(item == null){
            return;
        }
        cz.HackerGamingCZ.HackerTools.actions.Action itemAction = item.getAction();
        if(itemAction == null){
            return;
        }
        itemAction.cast(player);
    }

}
