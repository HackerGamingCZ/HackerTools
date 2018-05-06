package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener{

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        GameState state = HackerTools.getPlugin().getMinigameAPI().getGameState();
        Player player = e.getPlayer();
        if(state == null){
            return;
        }
        GameState.JoinType type = state.getJoinType();
        if(type == GameState.JoinType.NOBODY){
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            e.setKickMessage(Lang.get(Lang.JOINTYPE_NOBODY_KICK_MESSAGE));
            return;
        }
        if(type.getPermission() == null){
            return;
        }
        if(!player.hasPermission(type.getPermission())){
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            e.setKickMessage(Lang.get(Lang.JOINTYPE_PERMISSION_KICK_MESSAGE));
        }
    }

}
