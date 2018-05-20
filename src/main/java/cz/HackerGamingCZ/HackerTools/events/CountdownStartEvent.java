package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import org.bukkit.event.HandlerList;

public class CountdownStartEvent {

    private final SchedulerManager.SchedulerType type;
    private final int number;
    private static final HandlerList HANDLERS = new HandlerList();

    public CountdownStartEvent(int number, SchedulerManager.SchedulerType type) {
        this.type = type;
        this.number = number;
    }

    public int getNumber() {
        return number;
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

}
