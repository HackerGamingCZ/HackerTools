package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.api.SchedulerAPI;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import cz.HackerGamingCZ.HackerTools.events.CountdownEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(null);
        if(HackerTools.getPlugin().getMinigameAPI().isServerInLobby()){
            for(Player player : Bukkit.getOnlinePlayers()){
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, HackerTools.getPlugin().getPlaceholderAPI().replaceSpecialPlaceholder(Lang.LOBBY_DISCONNECT_INFO, Placeholder.ONLINEPLAYERS, String.valueOf(Bukkit.getOnlinePlayers().size()-1)), e.getPlayer().getName());
            }
        }
        Player player = e.getPlayer();
        int newPlayerCount = Bukkit.getOnlinePlayers().size()-1;
        if(newPlayerCount < HackerTools.getPlugin().getMinigameAPI().getMinPlayers() && HackerTools.getPlugin().getSchedulerAPI().getScheduler(SchedulerAPI.SchedulerType.LOBBY) != -1){
            HackerTools.getPlugin().getMinigameAPI().stopLobbyCoutdown(CountdownEndEvent.EndCause.PLAYER_DISCONNECT);
        }
    }

}
