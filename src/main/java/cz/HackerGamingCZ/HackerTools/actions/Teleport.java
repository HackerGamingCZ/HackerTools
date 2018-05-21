package cz.HackerGamingCZ.HackerTools.actions;

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
        if (getLocation() == null) {
            player.teleport(getPlayer());
            return;
        }
        player.teleport(getLocation());
    }

}
