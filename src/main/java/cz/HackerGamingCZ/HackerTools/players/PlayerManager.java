package cz.HackerGamingCZ.HackerTools.players;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {

    //PLAYERNAME => HTPLAYER
    private HashMap<String, HTPlayer> playerMap = new HashMap<>();

    public void addPlayer(Player player) {
        playerMap.put(player.getName(), new HTPlayer(player));
    }

    public HTPlayer getPlayer(Player player) {
        return playerMap.getOrDefault(player.getName(), null);
    }

    public HTPlayer getPlayer(String player) {
        return playerMap.getOrDefault(player, null);
    }

    public HashMap<String, HTPlayer> getPlayers() {
        return playerMap;
    }


}
