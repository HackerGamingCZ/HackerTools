package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.entity.Player;

public class OpenGUI extends Action {

    public OpenGUI(GUI gui) {
        super(gui);
    }

    @Override
    public void cast(Player player) {
        ((GUI)getObject()).open(player);
    }

}
