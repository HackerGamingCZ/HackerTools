package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeamListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {
            return;
        }
        Player victim = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();
        HTPlayer htVictim = HackerTools.getPlugin().getPlayerManager().getPlayer(victim);
        HTPlayer htDamager = HackerTools.getPlugin().getPlayerManager().getPlayer(damager);
        if (htVictim.getTeam() == null || htDamager.getTeam() == null) {
            return;
        }
        if (htDamager.getTeam() == htVictim.getTeam() && !HackerTools.getPlugin().getTeamManager().isTeamDamage()) {
            e.setCancelled(true);
            //TODO call TeamDamageEvent
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            return;
        }
        Team team = htPlayer.getTeam();
        if (team == null) {
            return;
        }
        if (!HackerTools.getPlugin().getTeamManager().isTeamChat()) {
            return;
        }
        e.setFormat("§8[" + team.getChatColor() + "TEAM§8] " + team.getChatColor() + "%s §8» " + team.getChatColor() + "%s");
    }

}
