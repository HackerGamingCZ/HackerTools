package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.actions.Action;

public class Event {

    private Action action;

    public Event(Action action) {
        this.action = action;
    }


    public Action getAction() {
        return action;
    }
}