package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.entity.Player;

public class OpenGUI extends Action {

    public OpenGUI(String value) {
        super(value);
    }

    @Override
    public void cast(Player player){
        player.performCommand(getValue());
        GUI gui = HackerTools.getPlugin().getGUIAPI().getGUI(getValue());
        if(gui == null){
            return;
        }
        gui.open(player);
    }

}
