package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.api.SchedulerAPI;
import org.bukkit.event.HandlerList;

public class CountdownStartEvent {

    private final SchedulerAPI.SchedulerType type;
    private final int number;
    private static final HandlerList HANDLERS = new HandlerList();

    public CountdownStartEvent(int number, SchedulerAPI.SchedulerType type) {
        this.type = type;
        this.number = number;
    }

    public int getNumber() {
        return number;
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

}
