package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.gui.GUI;
import org.bukkit.entity.Player;

public class OpenGUI extends Action {

    public OpenGUI(GUI gui) {
        super(gui);
    }

    @Override
    public void cast(Player player){
        getGui().open(player);
    }

}
