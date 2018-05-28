package cz.HackerGamingCZ.HackerTools.teams;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;

public interface Team extends Registrable {
    ChatColor getChatColor();

    byte getData();

    Color getColor();

    String getName();

    Location getTeamSpawn();

    default void teleportToSpawn(Player player) {
        player.teleport(getTeamSpawn());
    }

    default void teleportAllToSpawn() {
        for (HTPlayer player : getPlayers()) {
            teleportToSpawn(player.getPlayer());
        }
    }

    @Override
    default void register() {
        HackerTools.getPlugin().getTeamManager().addTeam(this);
    }

    default boolean canJoin(Player player, boolean usePermission) {
        if (usePermission && player.hasPermission(Permissions.TEAMS_FULLJOIN)) {
            return true;
        }
        int playersInThisTeam = getPlayers().size();
        for (Team team : HackerTools.getPlugin().getTeamManager().getTeams()) {
            if (team == this || team == HackerTools.getPlugin().getSpectatorTeam()) {
                continue;
            }
            int players = team.getPlayers().size();
            if (players < playersInThisTeam) {
                return false;
            }
        }
        return true;
    }

    default boolean canJoin(Player player) {
        return canJoin(player, true);
    }
    default void join(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        join(htPlayer);
    }

    default void join(HTPlayer htPlayer) {
        if (htPlayer == null) {
            return;
        }
        htPlayer.setTeam(this);
    }

    default void leave(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        leave(htPlayer);
    }

    default void leave(HTPlayer htPlayer) {
        if (htPlayer == null) {
            return;
        }
        htPlayer.setTeam(null);
    }

    default boolean isPlayerInTeam(Player player) {
        return getPlayers().contains(HackerTools.getPlugin().getPlayerManager().getPlayer(player));
    }

    default ArrayList<HTPlayer> getPlayers() {
        ArrayList<HTPlayer> players = new ArrayList<>();
        for (HTPlayer htPlayer : HackerTools.getPlugin().getPlayerManager().getPlayers().values()) {
            if (htPlayer.getTeam() == this) {
                players.add(htPlayer);
            }
        }
        return players;
    }

}