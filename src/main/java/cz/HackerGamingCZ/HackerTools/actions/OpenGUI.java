package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.GUI;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.apache.logging.log4j.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OpenGUI extends Action {

    public OpenGUI(String value, boolean asPlayer) {
        super(value, asPlayer);
    }

    @Override
    public void cast(Player player){
        player.performCommand(getValue());
        GUI gui = HackerTools.getPlugin().getGUIAPI().getGUI(getValue());
        if(gui == null){
            Bukkit.getLogger().warning("GUI "+getValue()+" doesn't exist!");
            return;
        }
        gui.open(player);
    }

}
