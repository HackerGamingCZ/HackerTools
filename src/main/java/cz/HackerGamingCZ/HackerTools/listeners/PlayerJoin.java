package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{

    private String message;

    public PlayerJoin(String message){
        this.message = message;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(message != null){
            this.message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(HackerTools.getPlugin().getPlaceholderApi().replaceString(this.message), Placeholder.PLAYERNAME, e.getPlayer().getName());
        }
        e.setJoinMessage(this.message);
    }
}
