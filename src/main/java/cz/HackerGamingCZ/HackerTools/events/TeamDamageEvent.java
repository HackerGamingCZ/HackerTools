package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class TeamDamageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private boolean cancelled = false;
    private HTPlayer damager;
    private HTPlayer victim;

    public TeamDamageEvent(HTPlayer damager, HTPlayer victim) {
        this.damager = damager;
        this.victim = victim;
    }

    public HTPlayer getDamager() {
        return damager;
    }

    public HTPlayer getVictim() {
        return victim;
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
