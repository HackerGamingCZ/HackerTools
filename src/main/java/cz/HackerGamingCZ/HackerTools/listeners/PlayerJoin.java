package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.api.SchedulerAPI;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        if(HackerTools.getPlugin().getMinigameAPI().isServerInLobby() && e.getPlayer().hasPermission(Permissions.HT_FORCESTART)){
            HackerTools.getPlugin().getItemInteractAPI().getItemByIdentificator("forcestart").giveItem(e.getPlayer().getInventory(), 8);
        }
        GameState state = HackerTools.getPlugin().getMinigameAPI().getGameState();
        if(state != null){
            if(state.getJoinType() == GameState.JoinType.SPECTATOR){
                HackerTools.getPlugin().getMinigameAPI().setSpectator(e.getPlayer(), HackerTools.getPlugin().getMinigameAPI().getSpectLocation());
            }
            if(state.getGlobalMessage() != null){
                for(Player player : Bukkit.getOnlinePlayers()){
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, state.getGlobalMessage(), e.getPlayer().getName());
                }
            }
            if(state.getMessageToPlayer() != null){
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(e.getPlayer(), state.getMessageToPlayer());
            }
        }
        int playerCount = Bukkit.getOnlinePlayers().size();
        if(HackerTools.getPlugin().getMinigameAPI().getMaxPlayers() == playerCount && HackerTools.getPlugin().getMinigameAPI().getCountdown() > 15){
            HackerTools.getPlugin().getMinigameAPI().setCountdown(15);
        }
        if(playerCount >= HackerTools.getPlugin().getMinigameAPI().getMinPlayers() && HackerTools.getPlugin().getSchedulerAPI().getScheduler(SchedulerAPI.SchedulerType.LOBBY) == -1){
            HackerTools.getPlugin().getMinigameAPI().startLobbyCountdown();
        } else{
            for(Player player : Bukkit.getOnlinePlayers()){
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.MIN_PLAYERS_INFO);
            }
        }
    }
}