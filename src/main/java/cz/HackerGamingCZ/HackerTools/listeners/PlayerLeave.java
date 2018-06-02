package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import cz.HackerGamingCZ.HackerTools.events.CountdownEndEvent;
import cz.HackerGamingCZ.HackerTools.events.TeamLeftGameEvent;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        int onlinePlayers = Bukkit.getOnlinePlayers().size()-1;
        if(HackerTools.getPlugin().getMinigameManager().getGameState() == GameState.INGAME){
            if(onlinePlayers <= 0){
                HackerTools.getPlugin().getLoggerManager().log("Every player left the server. Restarting in 3 seconds...");
                HackerTools.getPlugin().getSchedulerManager().runLater(()-> HackerTools.getPlugin().getServerManager().restart(), 20*3);
                return;
            }
        }
        if (HackerTools.getPlugin().getMinigameManager().isServerInLobby()) {
            HackerTools.getPlugin().getSchedulerManager().runLater(() -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.LOBBY_DISCONNECT_INFO, e.getPlayer().getName());
                }
            }, 3L);
        }
        Player player = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(e.getPlayer());
        if (htPlayer == null) {
            return;
        }
        int newPlayerCount = Bukkit.getOnlinePlayers().size() - 1;
        if (newPlayerCount < HackerTools.getPlugin().getMinigameManager().getMinPlayers() && HackerTools.getPlugin().getSchedulerManager().getScheduler(SchedulerManager.SchedulerType.LOBBY) != -1) {
            HackerTools.getPlugin().getMinigameManager().stopLobbyCoutdown(CountdownEndEvent.EndCause.PLAYER_DISCONNECT);
        }
        if (HackerTools.getPlugin().getPlayerManager().getPlayer(player).isSpectator()) {
            return;
        }
        Team team = htPlayer.getTeam();
        if (team == null) {
            return;
        }
        team.leave(player);
        if (team.getPlayers().size() <= 0) {
            if (HackerTools.getPlugin().getGameState() != GameState.INGAME) {
                return;
            }
            TeamLeftGameEvent event = new TeamLeftGameEvent(team);
            Bukkit.getPluginManager().callEvent(event);
        }
    }
}