package cz.HackerGamingCZ.HackerTools.teams;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface Team extends Registrable {
    ChatColor getChatColor();

    byte getData();

    Color getColor();

    String getName();

    default String getCodeName() {
        return getName();
    }

    Location getTeamSpawn();

    default boolean isIngameTeam() {
        return true;
    }

    default String getFullName(boolean bold) {
        return getChatColor() + "" + (bold ? ChatColor.BOLD : "") + getName();
    }


    default void teleportToSpawn(HTPlayer player) {
        teleportToSpawn(player.getPlayer());
    }

    default void teleportToSpawn(Player player) {
        player.teleport(getTeamSpawn());
    }

    default void teleportAllToSpawn() {
        for (HTPlayer player : getPlayers()) {
            teleportToSpawn(player);
        }
    }

    @Override
    default void register() {
        HackerTools.getPlugin().getTeamManager().addTeam(this);
    }

    default boolean canJoin(Player player, boolean usePermission) {
        return canJoin(HackerTools.getPlugin().getPlayerManager().getPlayer(player), usePermission);
    }

    default boolean canJoin(HTPlayer player, boolean usePermission) {
        if (player == null) {
            return false;
        }
        if (usePermission && Permissions.hasPermission(player, Permissions.TEAMS_FULLJOIN)) {
            return true;
        }
        if (this == HackerTools.getPlugin().getSpectatorTeam()) {
            return true;
        }
        int playersInThisTeam = getPlayers().size();
        for (Team team : HackerTools.getPlugin().getTeamManager().getTeams()) {
            if (team == this || team == HackerTools.getPlugin().getSpectatorTeam()) {
                continue;
            }
            int players = team.getPlayers().size();
            if (player.hasTeam()) {
                playersInThisTeam++;
            }
            if (players < playersInThisTeam) {
                return false;
            }
        }
        return true;
    }

    default boolean canJoin(HTPlayer player) {
        return canJoin(player, true);
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
        return isPlayerInTeam(HackerTools.getPlugin().getPlayerManager().getPlayer(player));
    }

    default boolean isPlayerInTeam(HTPlayer player) {
        return getPlayers().contains(player);
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