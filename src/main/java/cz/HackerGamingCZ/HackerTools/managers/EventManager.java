package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.listeners.*;
import cz.HackerGamingCZ.HackerTools.listeners.TeamListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private PluginManager pm = Bukkit.getPluginManager();
    private HackerTools plugin = HackerTools.getPlugin();

    public void denyInteract(Material... materials) {
        pm.registerEvents(new DenyInteract(materials), plugin);
    }

    public void enableVoidKills(int lowestY) {
        pm.registerEvents(new VoidDamage(lowestY), plugin);
    }

    //TODO
    public void denyCraft(Material... materials) {

    }

    //TODO
    public void denyAllCraft() {

    }

    public void denyAnimalKilling(EntityType... ignored) {
        pm.registerEvents(new AnimalsKills(ignored), plugin);
    }

    public void denyAnimalSpawning(CreatureSpawnEvent.SpawnReason... ignored) {
        pm.registerEvents(new AnimalSpawn(ignored), plugin);
    }

    //DEFAULTs
    public void registerInventoryClickEvent() {
        pm.registerEvents(new InventoryClick(), plugin);
    }

    public void registerPlayerLogin() {
        pm.registerEvents(new PlayerLogin(), plugin);
    }

    public void registerPlayerJoin() {
        pm.registerEvents(new PlayerJoin(), plugin);
    }

    public void registerEntityInteract() {
        pm.registerEvents(new EntityInteract(), plugin);
    }

    public void registerPlayerInteract() {
        pm.registerEvents(new PlayerInteract(), plugin);
    }

    public void registerPlayerLeave() {
        pm.registerEvents(new PlayerLeave(), plugin);
    }

    public void registerItemDrop() {
        pm.registerEvents(new ItemDrop(), plugin);
    }

    public void registerTeamListener() {
        pm.registerEvents(new TeamListener(), plugin);
    }

    public void registerEntityDamage() {
        pm.registerEvents(new EntityDamage(), plugin);
    }

    public void registerServerListPing() {
        pm.registerEvents(new ServerListPing(), plugin);
    }

    public void registerSpectator() {
        pm.registerEvents(new Spectator(), plugin);
    }

    public void registerLobbyListener() {
        pm.registerEvents(new Lobby(), plugin);
    }
}