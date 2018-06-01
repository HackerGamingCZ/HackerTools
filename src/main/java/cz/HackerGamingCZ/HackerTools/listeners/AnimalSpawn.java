package cz.HackerGamingCZ.HackerTools.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.Collections;

public class AnimalSpawn implements Listener {

    private ArrayList<CreatureSpawnEvent.SpawnReason> ignoredReasons = new ArrayList<>();

    public AnimalSpawn(CreatureSpawnEvent.SpawnReason... ignored) {
        ignoredReasons.add(CreatureSpawnEvent.SpawnReason.CUSTOM);
        Collections.addAll(ignoredReasons, ignored);
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        if (ignoredReasons.contains(e.getSpawnReason())) {
            return;
        }
        e.setCancelled(true);
    }

}
