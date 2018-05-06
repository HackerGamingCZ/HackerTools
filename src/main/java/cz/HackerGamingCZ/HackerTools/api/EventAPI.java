package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;

public class EventAPI {

    private PluginManager pm = Bukkit.getPluginManager();
    private HackerTools plugin = HackerTools.getPlugin();

    public void denyInteract(Material[] materials){
        pm.registerEvents(new DenyInteract(materials), HackerTools.getPlugin());
    }

    //TODO
    public void denyCraft(){

    }

    //DEFAULTs
    public void registerInventoryClickEvent(){
        pm.registerEvents(new InventoryClick(), plugin);
    }
    public void registerPlayerLogin(){
        pm.registerEvents(new PlayerLogin(), plugin);
    }
    public void registerPlayerJoin(){
        pm.registerEvents(new PlayerJoin(), plugin);
    }
    public void registerEntityInteract(){
        pm.registerEvents(new EntityInteract(), plugin);
    }
    public void registerPlayerInteract(){
        pm.registerEvents(new PlayerInteract(), plugin);
    }
    public void registerPlayerLeave(){
        pm.registerEvents(new PlayerLeave(), plugin);
    }

}