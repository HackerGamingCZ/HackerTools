package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.actions.Action;

public class ItemInInventoryClickEvent extends Event {

    public ItemInInventoryClickEvent(Action action, boolean canceled, boolean closingInventory) {
        super(action, canceled, closingInventory);
    }

}
