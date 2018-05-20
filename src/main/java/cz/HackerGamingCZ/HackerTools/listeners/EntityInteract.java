package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.entities.InteractableEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteract implements Listener {

    @EventHandler
    public void onInteractWithEntity(PlayerInteractEntityEvent e){
        Entity entity = e.getRightClicked();
        if(entity == null){
            return;
        }
        if(entity.getCustomName() == null){
            return;
        }
        InteractableEntity iEntity = HackerTools.getPlugin().getEntityInteractAPI().getEntity(entity.getName());
        if(iEntity == null){
            return;
        }
        iEntity.getAction().cast(e.getPlayer());
    }

}
