package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.listeners.DenyInteract;
import cz.HackerGamingCZ.HackerTools.listeners.InventoryClick;
import cz.HackerGamingCZ.HackerTools.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;

public class EventAPI {

    private PluginManager pm = Bukkit.getPluginManager();

    public void denyInteract(Material[] materials){
        pm.registerEvents(new DenyInteract(materials), HackerTools.getPlugin());
    }

    public void denyJoinMessage(){
        pm.registerEvents(new PlayerJoin(null), HackerTools.getPlugin());
    }

    public void customJoinMessage(String message){
        pm.registerEvents(new PlayerJoin(message), HackerTools.getPlugin());
    }

    //TODO
    public void denyCraft(){

    }

    public void registerInventoryClickEvent(){
        pm.registerEvents(new InventoryClick(), HackerTools.getPlugin());
    }

}
