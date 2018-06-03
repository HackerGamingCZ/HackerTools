package cz.HackerGamingCZ.HackerTools.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;


public class EntityExplosion implements Listener {

    private boolean damage;
    private boolean effect;

    public EntityExplosion(boolean damage, boolean effect) {
        this.damage = damage;
        this.effect = effect;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (effect) {
            e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
        }
        e.setCancelled(true);
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (((e.getEntity() instanceof Player)) && (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))) {
            if (!damage) {
                e.setCancelled(true);
            }
        }
    }

}
