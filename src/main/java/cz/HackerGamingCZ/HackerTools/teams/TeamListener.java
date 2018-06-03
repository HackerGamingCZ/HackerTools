package cz.HackerGamingCZ.HackerTools.teams;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.events.PlayerChatMessageEvent;
import cz.HackerGamingCZ.HackerTools.events.TeamDamageEvent;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
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
            TeamDamageEvent event = new TeamDamageEvent();
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player sender = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(sender);
        if (htPlayer == null) {
            return;
        }
        Team team = htPlayer.getTeam();
        PlayerChatMessageEvent event = new PlayerChatMessageEvent(htPlayer, e.getMessage());
        Bukkit.getPluginManager().callEvent(event);
        if (team == null || HackerTools.getPlugin().getGameState() != GameState.INGAME || !HackerTools.getPlugin().getTeamManager().isTeamChat()) {
            e.setFormat(event.getPrefix() + "%s §8» §7" + event.getSuffix() + "%s");
            return;
        }
        if (String.valueOf(e.getMessage().toCharArray()[0]).equals(HackerTools.getPlugin().getChatManager().getDistributor()) && team != HackerTools.getPlugin().getSpectatorTeam()) {
            e.setMessage(e.getMessage().replaceFirst(HackerTools.getPlugin().getChatManager().getDistributor(), ""));
            e.setFormat("§8[" + team.getChatColor() + "ALL§8] " + event.getPrefix() + team.getChatColor() + "%s §8» " + team.getChatColor() + event.getSuffix() + "%s");
            HackerTools.getPlugin().getLoggerManager().log("[ALL] " + sender.getName() + " » " + e.getMessage());
            return;
        }
        e.setCancelled(true);
        for (HTPlayer htPlayer1 : team.getPlayers()) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(htPlayer1.getPlayer(), "§8[" + team.getChatColor() + "TEAM§8] " + event.getPrefix() + team.getChatColor() + htPlayer.getPlayer().getName() + " §8» " + team.getChatColor() + event.getSuffix() + e.getMessage());
            HackerTools.getPlugin().getLoggerManager().log("[" + team.getName() + "] " + sender.getName() + " » " + e.getMessage());
        }
    }

    @EventHandler
    public void arrowDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Arrow)) {
            return;
        }
        Arrow arrow = (Arrow) e.getDamager();
        if (arrow == null || !(arrow.getShooter() instanceof Player)) {
            return;
        }
        HTPlayer shooter = HackerTools.getPlugin().getPlayerManager().getPlayer((Player) arrow.getShooter());
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        HTPlayer entity = HackerTools.getPlugin().getPlayerManager().getPlayer((Player) e.getEntity());
        if (entity.getTeam() == null || shooter.getTeam() == null) {
            return;
        }
        if (HackerTools.getPlugin().getTeamManager().isTeamDamage()) {
            return;
        }
        if (entity.getTeam().isPlayerInTeam(shooter.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
