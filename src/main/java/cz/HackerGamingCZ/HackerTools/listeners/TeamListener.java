package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.events.TeamDamageEvent;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
            TeamDamageEvent event = new TeamDamageEvent();
            Bukkit.getPluginManager().callEvent(event);
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
        if (team == null || !HackerTools.getPlugin().getTeamManager().isTeamChat()) {
            e.setFormat("§7%s §8» §7%s");
            return;
        }
        if (String.valueOf(e.getMessage().toCharArray()[0]).equals(HackerTools.getPlugin().getChatManager().getDistributor()) && team != HackerTools.getPlugin().getSpectatorTeam()) {
            e.setMessage(e.getMessage().replaceFirst(HackerTools.getPlugin().getChatManager().getDistributor(), ""));
            e.setFormat("§8[" + team.getChatColor() + "ALL§8] " + team.getChatColor() + "%s §8» " + team.getChatColor() + "%s");
            return;
        }
        e.setCancelled(true);
        for (HTPlayer htPlayer1 : team.getPlayers()) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(htPlayer1.getPlayer(), "§8[" + team.getChatColor() + "TEAM§8] " + team.getChatColor() + htPlayer.getPlayer().getName() + " §8» " + team.getChatColor() + e.getMessage());
        }
    }

}
