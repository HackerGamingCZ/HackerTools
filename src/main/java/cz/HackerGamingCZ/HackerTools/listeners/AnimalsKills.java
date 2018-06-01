package cz.HackerGamingCZ.HackerTools.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Collections;

public class AnimalsKills implements Listener {

    private ArrayList<EntityType> ignoredTypes = new ArrayList<>();

    public AnimalsKills(EntityType... ignored) {
        Collections.addAll(ignoredTypes, ignored);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        EntityType entityType = e.getEntity().getType();
        if (ignoredTypes.contains(entityType)) {
            return;
        }
        e.setCancelled(true);
    }

}
