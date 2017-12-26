package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static final String allowedNick = "HackerGamingCZ";
    private static ItemManager itemManager = new ItemManager();

    @Override
    public void onEnable(){
        plugin = this;
        Bukkit.getLogger().warning("HackerTools support enabled!");
    }

    @Override
    public void onDisable(){

        Bukkit.getLogger().warning("HackerTools support disabled!");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static boolean hasPermission(Player player) {
        return player.getName().equals(allowedNick);
    }
    public static boolean hasPermission(String player) {
        return player.equals(allowedNick);
    }


    //MANAGERS
    public static ItemManager getItemManager() {
        return itemManager;
    }
}
