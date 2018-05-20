package cz.HackerGamingCZ.HackerTools.teams;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface Team {
    ChatColor getChatColor();
    byte getData();
    Color getColor();
    String getName();
    Location getTeamSpawn();

    default void teleportToSpawn(Player player){
        player.teleport(getTeamSpawn());
    }

    default void teleportAllToSpawn(){
        for(HTPlayer player : getPlayers()){
            teleportToSpawn(player.getPlayer());
        }
    }

    default void join(Player player){
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        join(htPlayer);
    }

    default void join(HTPlayer htPlayer){
        if(htPlayer == null){
            return;
        }
        htPlayer.setTeam(this);
    }

    default void leave(Player player){
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        leave(htPlayer);
    }

    default void leave(HTPlayer htPlayer){
        if(htPlayer == null){
            return;
        }
        htPlayer.setTeam(null);
    }

    default boolean isPlayerInTeam(Player player){
        return getPlayers().contains(HackerTools.getPlugin().getPlayerManager().getPlayer(player));
    }

    default ArrayList<HTPlayer> getPlayers(){
        ArrayList<HTPlayer> players = new ArrayList<>();
        for(HTPlayer htPlayer : HackerTools.getPlugin().getPlayerManager().getPlayers().values()){
            if(htPlayer.getTeam() == this){
                players.add(htPlayer);
            }
        }
        return players;
    }

}