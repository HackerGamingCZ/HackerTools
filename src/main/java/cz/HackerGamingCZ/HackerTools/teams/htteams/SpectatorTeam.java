package cz.HackerGamingCZ.HackerTools.teams.htteams;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpectatorTeam implements Team {


    @Override
    public ChatColor getChatColor() {
        return ChatColor.GRAY;
    }

    @Override
    public byte getData() {
        return 8;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    @Override
    public String getName() {
        return "Spectator";
    }

    @Override
    public Location getTeamSpawn() {
        return HackerTools.getPlugin().getMinigameManager().getSpectLocation();
    }

    @Override
    public boolean isIngameTeam() {
        return false;
    }

    @Override
    public void join(HTPlayer htPlayer) {
        if (htPlayer == null) {
            return;
        }
        htPlayer.setTeam(this);
        Player player = htPlayer.getPlayer();
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        if (getTeamSpawn() != null) {
            player.teleport(getTeamSpawn());
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
        HackerTools.getPlugin().getSpectatorPlayerListItem().giveItem(player.getInventory(), 8);
        HackerTools.getPlugin().getSpectatorSettingsItem().giveItem(player.getInventory(), 4);
    }

}
