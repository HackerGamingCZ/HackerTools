package cz.HackerGamingCZ.HackerTools.API;

import cz.HackerGamingCZ.HackerTools.Main;
import cz.HackerGamingCZ.HackerTools.listeners.DenyInteract;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;

public class Events {

    private static PluginManager pm = Bukkit.getPluginManager();

    public static void denyInteract(Material[] materials, String[] ignoredPlayers){
        pm.registerEvents(new DenyInteract(materials, ignoredPlayers), Main.getPlugin());
    }

}
