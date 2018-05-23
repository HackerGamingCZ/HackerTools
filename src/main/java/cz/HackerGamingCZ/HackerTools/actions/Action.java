package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Action {

    private String value;
    private boolean asPlayer;
    private Object object;

    protected Action(String value, boolean asPlayer) {
        this.value = value;
        this.asPlayer = asPlayer;
    }

    protected Action(Object object){
        this.object = object;
    }

    public String getValue() {
        return value;
    }

    public void cast(Player player) {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAsPlayer() {
        return asPlayer;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}

