package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.api.SchedulerAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountdownEndEvent extends Event{

    private final EndCause cause;
    private final SchedulerAPI.SchedulerType type;
    private static final HandlerList HANDLERS = new HandlerList();

    public CountdownEndEvent(EndCause cause, SchedulerAPI.SchedulerType type) {
        this.cause = cause;
        this.type = type;
    }

    public EndCause getCause() {
        return cause;
    }

    public SchedulerAPI.SchedulerType getType() {
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
