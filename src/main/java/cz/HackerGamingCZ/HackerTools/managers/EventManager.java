package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.Main;
import cz.HackerGamingCZ.HackerTools.listeners.DenyInteract;
import cz.HackerGamingCZ.HackerTools.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private PluginManager pm = Bukkit.getPluginManager();

    public void denyInteract(Material[] materials, String[] ignoredPlayers){
        pm.registerEvents(new DenyInteract(materials, ignoredPlayers), Main.getPlugin());
    }

    public void denyJoinMessage(){
        pm.registerEvents(new PlayerJoin(null), Main.getPlugin());
    }

    public void customJoinMessage(String message){
        pm.registerEvents(new PlayerJoin(message), Main.getPlugin());
    }

}
