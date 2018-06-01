package cz.HackerGamingCZ.HackerTools.debug;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DebugManager {

    //ARGUMENT >> DEBUG INSTANCE
    private HashMap<String, Debug> debug = new HashMap<>();

    public DebugManager() {
        addDebug("state", new Debug() {
            @Override
            public String getOutput() {
                return String.valueOf(HackerTools.getPlugin().getGameState());
            }

            @Override
            public String getDescription() {
                return "Current server state";
            }
        });
        //TODO add more default debugs
    }

    public void addDebug(String argument, Debug debug) {
        this.debug.put(argument, debug);
    }

    public void removeDebug(String argument) {
        debug.remove(argument);
    }

    public Debug getDebug(String argument) {
        for (String arg : debug.keySet()) {
            if (argument.equalsIgnoreCase(arg)) {
                return debug.get(arg);
            }
        }
        return null;
    }

    public boolean contains(String argument) {
        return debug.containsKey(argument);
    }

    public HashMap<String, Debug> getAllDebugs() {
        return debug;
    }

    public void sendDebug(Object message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Permissions.hasPermission(player, Permissions.DEBUG_SHOW)) {
                player.sendMessage(Lang.DEBUG_PREFIX + String.valueOf(message));
            }
        }
        HackerTools.getPlugin().getLoggerManager().log("DEBUG " + String.valueOf(message));
    }

    public void sendDebug(String identificator, Object message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Permissions.hasPermission(player, Permissions.DEBUG_SHOW)) {
                player.sendMessage(Lang.DEBUG_PREFIX + ChatColor.LIGHT_PURPLE + identificator + "§7: " + String.valueOf(message));
            }
        }
        HackerTools.getPlugin().getLoggerManager().log("DEBUG " + identificator + ": " + String.valueOf(message));
    }
}