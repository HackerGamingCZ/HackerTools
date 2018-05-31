package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Lobby implements Listener {

    @EventHandler
    public void damage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            Location lobbyLoc = HackerTools.getPlugin().getMinigameManager().getLobbyLocation();
            if (lobbyLoc == null) {
                return;
            }
            e.setCancelled(true);
            e.getEntity().teleport(lobbyLoc);
        }
    }

}
