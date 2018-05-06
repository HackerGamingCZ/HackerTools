package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.api.SchedulerAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountdownUpdateEvent extends Event {

    private final SchedulerAPI.SchedulerType type;
    private final int number;
    private static final HandlerList HANDLERS = new HandlerList();
    private final UpdateCause cause;

    public CountdownUpdateEvent(int number, SchedulerAPI.SchedulerType type, UpdateCause cause) {
        this.type = type;
        this.number = number;
        this.cause = cause;
    }

    public int getNumber() {
        return number;
    }

    public UpdateCause getCause() {
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

    public enum UpdateCause{
        FORCE,
        SCHEDULER_CYCLE
    }

}
