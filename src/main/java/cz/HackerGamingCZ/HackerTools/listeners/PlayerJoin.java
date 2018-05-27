package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.managers.ChatManager;
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
        GameState state = HackerTools.getPlugin().getGameState();
        Player player = e.getPlayer();
        e.setJoinMessage(null);
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            HackerTools.getPlugin().getPlayerManager().addPlayer(player);
            htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        } else {
            htPlayer.setPlayer(player);
        }
        if(state == GameState.NONE){
            return;
        }
        HackerTools.getPlugin().getMinigameManager().resetPlayer(player);
        if (HackerTools.getPlugin().getMinigameManager().isServerInLobby()) {
            if(player.hasPermission(Permissions.HT_FORCESTART)) {
                HackerTools.getPlugin().getForcestartItem().giveItem(player.getInventory(), 8);
            }
            if(HackerTools.getPlugin().getMinigameManager().getLobbyLocation() != null){
                player.teleport(HackerTools.getPlugin().getMinigameManager().getLobbyLocation());
            }
        }
        GameState.JoinType type = state.getJoinType();
        if (state.getJoinType() == GameState.JoinType.SPECTATOR) {
            HackerTools.getPlugin().getSpectatorTeam().join(htPlayer);
            if (GameState.JoinType.SPECTATOR.getGlobalMessage() != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, GameState.JoinType.SPECTATOR.getGlobalMessage(), player.getName());
                }
            }
            if (GameState.JoinType.SPECTATOR.getMessageToPlayer() != null) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, GameState.JoinType.SPECTATOR.getMessageToPlayer());
            }
        } else if (state.getJoinType() == GameState.JoinType.RECONNECT) {
            if (htPlayer.getPreviousTeam() != null && htPlayer.getPreviousTeam() != HackerTools.getPlugin().getSpectatorTeam()) {
                htPlayer.reconnect();
            } else {
                HackerTools.getPlugin().getSpectatorTeam().join(htPlayer);
                if (GameState.JoinType.SPECTATOR.getGlobalMessage() != null) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, GameState.JoinType.SPECTATOR.getGlobalMessage(), player.getName());
                    }
                }
                if (GameState.JoinType.SPECTATOR.getMessageToPlayer() != null) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, GameState.JoinType.SPECTATOR.getMessageToPlayer());
                }
            }
        }
        if (state != GameState.INGAME) {
            if (type.getGlobalMessage() != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, type.getGlobalMessage(), player.getName());
                }
            }
            if (type.getMessageToPlayer() != null) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, type.getMessageToPlayer());
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
        } else if(HackerTools.getPlugin().getMinigameManager().isServerInLobby()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Lang.MIN_PLAYERS_INFO);
            }
        }
    }
}