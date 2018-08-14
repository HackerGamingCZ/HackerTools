package cz.HackerGamingCZ.HackerTools.debug;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.ChatColor;

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
        sendDebug("", message);
    }

    public void sendDebug(String identificator, Object message) {
        for (HTPlayer player : HackerTools.getPlugin().getPlayerManager().getPlayers().values()) {
            if (!player.isDebug()) {
                continue;
            }
            if (Permissions.hasPermission(player, Permissions.DEBUG_SHOW)) {
                player.getPlayer().sendMessage(Lang.DEBUG_PREFIX + ChatColor.LIGHT_PURPLE + identificator + "§7: " + String.valueOf(message));
            }
        }
        HackerTools.getPlugin().getLoggerManager().log("DEBUG " + identificator + ": " + String.valueOf(message));
    }
}