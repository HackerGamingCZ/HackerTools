package cz.HackerGamingCZ.HackerTools.actions;

import org.bukkit.entity.Player;

public class PerformCommand extends Action {

    public PerformCommand(String value) {
        super(value);
        setValue(getValue().replace("/", ""));
    }

    @Override
    public void cast(Player player){
        player.performCommand(getValue());
    }
}
