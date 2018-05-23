package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport extends Action {

    public Teleport(Location location) {
        super(location);
    }

    public Teleport(Player player) {
        super(player);
    }

    @Override
    public void cast(Player player) {
        if (getObject() == null) {
            player.teleport(player);
            return;
        }
        player.teleport(((Location)getObject()));
    }

}
