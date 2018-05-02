package cz.HackerGamingCZ.HackerTools.actions;

import org.bukkit.entity.Player;

public class Action {

    private String value;

    public Action(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void cast(Player player){ }

    public void setValue(String value) {
        this.value = value;
    }
}

