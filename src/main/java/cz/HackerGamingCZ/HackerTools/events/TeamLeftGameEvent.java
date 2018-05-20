package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamLeftGameEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Team team;

    public TeamLeftGameEvent(Team team) {
        this.team = team;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Team getTeam() {
        return team;
    }

}