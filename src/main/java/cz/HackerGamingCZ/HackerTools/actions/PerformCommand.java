package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PerformCommand extends Action {

    public PerformCommand(String value, boolean asPlayer) {
        super(value, asPlayer);
        setValue(getValue().replace("/", ""));
    }

    @Override
    public void cast(Player player){
        if(isAsPlayer()){
            player.performCommand(getValue());
            return;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getValue().replaceFirst("/", ""));
    }
}