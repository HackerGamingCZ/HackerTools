package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.entities.InteractableEntity;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteract implements Listener {

    @EventHandler
    public void onInteractWithEntity(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (entity == null) {
            return;
        }
        if (entity.getCustomName() == null) {
            return;
        }
        InteractableEntity iEntity = HackerTools.getPlugin().getEntityInteractManager().getEntity(entity.getName());
        if (iEntity == null) {
            return;
        }
        Player player = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            return;
        }
        iEntity.getAction().execute(htPlayer);
    }

}
