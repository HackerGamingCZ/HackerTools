package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.actions.JoinTeam;
import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        HackerTools.getPlugin().getMinigameManager().resetPlayer(e.getPlayer());
        e.setJoinMessage(null);
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(e.getPlayer());
        if (htPlayer == null) {
            HackerTools.getPlugin().getPlayerManager().addPlayer(e.getPlayer());
            htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(e.getPlayer());
        } else {
            htPlayer.setPlayer(e.getPlayer());
        }
        if (HackerTools.getPlugin().getMinigameManager().isServerInLobby() && e.getPlayer().hasPermission(Permissions.HT_FORCESTART)) {
            HackerTools.getPlugin().getForcestartItem().giveItem(e.getPlayer().getInventory(), 8);
        }
        GameState state = HackerTools.getPlugin().getMinigameManager().getGameState();

        GameState.JoinType type = state.getJoinType();
        if (state.getJoinType() == GameState.JoinType.SPECTATOR) {
            HackerTools.getPlugin().getSpectatorTeam().join(htPlayer);
        } else if(state.getJoinType() == GameState.JoinType.RECONNECT){
            if(htPlayer.getPreviousTeam() != null && htPlayer.getPreviousTeam() != HackerTools.getPlugin().getSpectatorTeam()){
                htPlayer.reconnect();
            } else{
                HackerTools.getPlugin().getSpectatorTeam().join(htPlayer);
            }
        }
        if(state != GameState.INGAME) {
            if (type.getGlobalMessage() != null) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, type.getGlobalMessage(), e.getPlayer().getName());
                }
            }
            if (type.getMessageToPlayer() != null) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(e.getPlayer(), type.getMessageToPlayer());
            }
        }
        int playerCount = Bukkit.getOnlinePlayers().size();
        if (HackerTools.getPlugin().getMinigameManager().getMaxPlayers() == playerCount && HackerTools.getPlugin().getMinigameManager().getCountdown() > 15) {
            HackerTools.getPlugin().getMinigameManager().setCountdown(15);
        }
        if (playerCount == HackerTools.getPlugin().getMinigameManager().getMinPlayers()) {
            if (HackerTools.getPlugin().getSchedulerManager().getScheduler(SchedulerManager.SchedulerType.LOBBY) == -1) {
                HackerTools.getPlugin().getMinigameManager().startLobbyCountdown();
            }
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.MIN_PLAYERS_INFO);
            }
        }
    }
}