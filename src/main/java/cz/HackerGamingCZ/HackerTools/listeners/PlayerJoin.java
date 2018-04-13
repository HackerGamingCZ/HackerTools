package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.Main;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{

    private String message;

    public PlayerJoin(String message){
        this.message = message;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        this.message = Main.getPlugin().getPlaceholderManager().replaceSpecialPlaceholder(Main.getPlugin().getPlaceholderManager().replaceString(this.message), Placeholder.PLAYERNAME, "");
        e.setJoinMessage(this.message);
    }
}
