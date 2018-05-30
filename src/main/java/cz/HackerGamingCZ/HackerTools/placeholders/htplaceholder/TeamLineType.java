package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.scoreboard.linetype.LineType;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.entity.Player;

public class TeamLineType implements LineType {

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getText(HTPlayer player) {
        Team team = player.getTeam();
        if (team == null) {
            return null;
        }
        return team.getChatColor() + team.getName();
    }

    @Override
    public String getText(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        return getText(htPlayer);
    }
}
