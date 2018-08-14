package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReconnectEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private HTPlayer player;

    public PlayerReconnectEvent(HTPlayer player) {
        this.player = player;
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
