package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.events.PlayerReconnectEvent;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        GameState state = HackerTools.getPlugin().getGameState();
        Player player = e.getPlayer();
        if (state == null) {
            return;
        }
        GameState.JoinType type = state.getJoinType();
        if (type == GameState.JoinType.NOBODY) {
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            e.setKickMessage(Lang.get(Lang.JOINTYPE_NOBODY_KICK_MESSAGE));
            return;
        }
        if (type.getPermission() != null) {
            if (!player.hasPermission(type.getPermission())) {
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(Lang.get(Lang.JOINTYPE_PERMISSION_KICK_MESSAGE));
                return;
            }
        }
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null || htPlayer.getPreviousTeam() == null || htPlayer.getPreviousTeam() == HackerTools.getPlugin().getSpectatorTeam()) {
            return;
        }
        if (state == GameState.INGAME && type == GameState.JoinType.RECONNECT) {
            PlayerReconnectEvent event = new PlayerReconnectEvent(htPlayer);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

}
