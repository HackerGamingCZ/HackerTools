package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)){
            return;
        }
        Player victim = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();
        if(victim == null || damager == null){
            return;
        }
        HTPlayer htVictim = HackerTools.getPlugin().getPlayerManager().getPlayer(victim);
        if(htVictim == null){
            return;
        }
        htVictim.setLastHittedBy(HackerTools.getPlugin().getPlayerManager().getPlayer(damager));
    }

}
