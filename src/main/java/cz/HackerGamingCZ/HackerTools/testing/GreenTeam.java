package cz.HackerGamingCZ.HackerTools.testing;

import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;

public class GreenTeam implements Team {

    @Override
    public ChatColor getChatColor() {
        return ChatColor.GREEN;
    }

    @Override
    public byte getData() {
        return 0;
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public String getName() {
        return "Green";
    }

    @Override
    public Location getTeamSpawn() {
        return null;
    }
}