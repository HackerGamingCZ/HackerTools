package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinTeamEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private Team team;
    private HTPlayer player;
    private boolean cancelled = false;

    public PlayerJoinTeamEvent(Team team, HTPlayer player) {
        this.team = team;
        this.player = player;
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

    public HTPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
