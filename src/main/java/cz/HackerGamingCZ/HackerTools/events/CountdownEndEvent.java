package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountdownEndEvent extends Event {

    private final EndCause cause;
    private final SchedulerManager.SchedulerType type;
    private static final HandlerList HANDLERS = new HandlerList();

    public CountdownEndEvent(EndCause cause, SchedulerManager.SchedulerType type) {
        this.cause = cause;
        this.type = type;
    }

    public EndCause getCause() {
        return cause;
    }

    public SchedulerManager.SchedulerType getType() {
        return type;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public enum EndCause{
        PLAYER_DISCONNECT,
        SCHEDULER_END
    }
}
