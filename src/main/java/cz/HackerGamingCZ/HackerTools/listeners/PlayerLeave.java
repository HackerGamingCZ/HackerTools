package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
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
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(null);
        if(HackerTools.getPlugin().getMinigameManager().isServerInLobby()){
            for(Player player : Bukkit.getOnlinePlayers()){
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, HackerTools.getPlugin().getPlaceholderAPI().replaceSpecialPlaceholder(Lang.LOBBY_DISCONNECT_INFO, Placeholder.ONLINEPLAYERS, String.valueOf(Bukkit.getOnlinePlayers().size()-1)), e.getPlayer().getName());
            }
        }
        Player player = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(e.getPlayer());
        if(htPlayer == null){
            return;
        }
        if(htPlayer.getTeam() != null) {
            htPlayer.getTeam().leave(htPlayer);
        }
        int newPlayerCount = Bukkit.getOnlinePlayers().size()-1;
        if(newPlayerCount < HackerTools.getPlugin().getMinigameManager().getMinPlayers() && HackerTools.getPlugin().getSchedulerManager().getScheduler(SchedulerManager.SchedulerType.LOBBY) != -1){
            HackerTools.getPlugin().getMinigameManager().stopLobbyCoutdown(CountdownEndEvent.EndCause.PLAYER_DISCONNECT);
        }
        if(HackerTools.getPlugin().getPlayerManager().getPlayer(player).isSpectator()){
            return;
        }
        Team team = HackerTools.getPlugin().getPlayerManager().getPlayer(player).getTeam();
        if(team == null){
            return;
        }

        for(HTPlayer p : team.getPlayers()){
            if(p.getPlayer().isOnline() && p.getPlayer() != player){
                return;
            }
        }
        TeamLeftGameEvent event = new TeamLeftGameEvent(team);
        Bukkit.getPluginManager().callEvent(event);
    }

}