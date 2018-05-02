package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.actions.Action;

public class Event {

    private boolean closingInventory;
    private Action action;
    private boolean canceled;

    public Event(Action action, boolean cancel, boolean closingInventory) {
        this.action = action;
        this.closingInventory = closingInventory;
        this.canceled = cancel;
    }


    public Action getAction() {
        return action;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isClosingInventory() {
        return closingInventory;
    }
}