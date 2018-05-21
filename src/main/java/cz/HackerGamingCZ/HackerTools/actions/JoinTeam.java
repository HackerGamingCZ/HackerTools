package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.entity.Player;

public class JoinTeam extends Action {

    public JoinTeam(Team team) {
        super(team);
    }

    @Override
    public void cast(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        getTeam().join(htPlayer);
    }

}
