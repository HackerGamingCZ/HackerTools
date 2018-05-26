package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DebugManager {

    //ARGUMENT >> OUTPUT
    private HashMap<String, String> debug = new HashMap<>();

    public void addDebug(String argument, String output) {
        debug.put(argument, output);
    }

    public void removeDebug(String argument) {
        debug.remove(argument);
    }

    public String getDebug(String argument) {
        return debug.get(argument);
    }

    public boolean contains(String argument) {
        return debug.containsKey(argument);
    }

    public HashMap<String, String> getAllDebugs() {
        return debug;
    }

    public void sendDebug(Object message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (HackerTools.getPlugin().hasSpecialPermission(player)) {
                player.sendMessage(Lang.DEBUG_PREFIX + String.valueOf(message));
            }
        }
    }

    public void sendDebug(String identificator, Object message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (HackerTools.getPlugin().hasSpecialPermission(player)) {
                player.sendMessage(Lang.DEBUG_PREFIX + ChatColor.GREEN + identificator + "§7: " + String.valueOf(message));
            }
        }
    }
}