package cz.HackerGamingCZ.HackerTools.players;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerManager {

    //PLAYERNAME => HTPLAYER
    private HashMap<String, HTPlayer> playerMap = new HashMap<>();
    private File playerFolder;

    public PlayerManager() {
        playerFolder = new File(HackerTools.getPlugin().getDataFolder(), "players");
        if (!playerFolder.exists()) {
            if (playerFolder.mkdir()) {
                HackerTools.getPlugin().getLoggerManager().log("Folder players created!");
            } else {
                HackerTools.getPlugin().getLoggerManager().warn("Folder players could not be created!");
            }
        }
    }

    public void addPlayer(Player player) {
        playerMap.put(player.getName(), new HTPlayer(player));
    }

    public HTPlayer getPlayer(Player player) {
        if (player == null) {
            return null;
        }
        return getPlayer(player.getName());
    }

    public HTPlayer getPlayer(String player) {
        return playerMap.getOrDefault(player, null);
    }

    public ArrayList<HTPlayer> getIngamePlayers() {
        ArrayList<HTPlayer> players = new ArrayList<>();
        for (HTPlayer player : getPlayers().values()) {
            if (player.isSpectator()) {
                continue;
            }
            if (player.getTeam() == null) {
                continue;
            }
            players.add(player);
        }
        return players;
    }

    public HashMap<String, HTPlayer> getPlayers() {
        return playerMap;
    }

    public File getPlayerFolder() {
        return playerFolder;
    }
}
