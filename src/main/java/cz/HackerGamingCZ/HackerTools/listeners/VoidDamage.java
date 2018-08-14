package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.events.DeathByVoidEvent;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidDamage implements Listener {
    private int lowestY = 0;

    public VoidDamage(int lowestY) {
        this.lowestY = lowestY;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        long toY = Math.round(e.getTo().getY());
        if (toY <= this.lowestY) {
            HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(e.getPlayer());
            if (htPlayer == null) {
                return;
            }
            if (HackerTools.getPlugin().getMinigameManager().isServerInLobby()) {
                return;
            }
            if (htPlayer.isSpectator()) {
                return;
            }
            if (htPlayer.getPlayer().isDead()) {
                return;
            }
            DeathByVoidEvent event = new DeathByVoidEvent(htPlayer, htPlayer.getLastHittedBy());
            Bukkit.getPluginManager().callEvent(event);
            htPlayer.setLastHittedBy(null);
        }
    }

}