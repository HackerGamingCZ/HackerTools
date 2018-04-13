package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DebugManager {

    //ARGUMENT >> OUTPUT
    private HashMap<String, String> debug = new HashMap<>();

    public void addDebug(String argument, String output){
        debug.put(argument, output);
    }
    public void removeDebug(String argument){
        debug.remove(argument);
    }
    public String getDebug(String argument){
        return debug.get(argument);
    }
    public boolean contains(String argument){
        return debug.containsKey(argument);
    }
    public HashMap<String, String> getAllDebugs(){
        return debug;
    }
    public void sendDebug(String message){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(Main.getPlugin().hasPermission(player)){
                player.sendMessage(Lang.DEBUG_PREFIX+message);
            }
        }
    }

}