package cz.HackerGamingCZ.HackerTools.actions;

import org.bukkit.entity.Player;

public class Action {

    private String value;
    private boolean asPlayer;

    public Action(String value, boolean asPlayer){
        this.value = value;
        this.asPlayer = asPlayer;
    }

    public String getValue() {
        return value;
    }

    public void cast(Player player){ }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAsPlayer() {
        return asPlayer;
    }

    public void setAsPlayer(boolean asPlayer) {
        this.asPlayer = asPlayer;
    }
}

