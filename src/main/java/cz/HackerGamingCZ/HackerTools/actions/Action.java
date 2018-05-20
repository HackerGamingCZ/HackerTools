package cz.HackerGamingCZ.HackerTools.actions;

import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Action {

    private String value;
    private boolean asPlayer;
    private GUI gui;
    private Location location;
    private Player player;
    private Team team;

    protected Action(String value, boolean asPlayer){
        this.value = value;
        this.asPlayer = asPlayer;
    }

    public Action(Player player){
        this.player = player;
    }
    public Action(Team team){
        this.team = team;
    }

    public Action(Location location){
        this.location = location;
    }

    public Action(GUI gui){
        this.gui = gui;
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
    public GUI getGui() {
        return gui;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }
}

