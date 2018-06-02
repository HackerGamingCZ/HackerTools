package cz.HackerGamingCZ.HackerTools.managers;

import org.bukkit.Bukkit;

public class ServerManager {

    public void restart(){
        Bukkit.getServer().shutdown();
    }

}
