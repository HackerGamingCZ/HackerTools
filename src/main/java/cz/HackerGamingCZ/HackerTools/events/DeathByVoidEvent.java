package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeathByVoidEvent extends Event{

    private static final HandlerList HANDLERS = new HandlerList();
    private HTPlayer player;
    private HTPlayer killer;

    public DeathByVoidEvent(HTPlayer player, HTPlayer killer) {
        this.player = player;
        this.killer = killer;
    }

    public HTPlayer getKiller() {
        return killer;
    }

    public HTPlayer getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


}
