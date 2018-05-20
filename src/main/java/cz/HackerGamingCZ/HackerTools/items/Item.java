package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.inventory.ItemStack;

public class Item {

    private ItemStack is;
    private Event event;
    private boolean canceled;
    private boolean closingInventory;
    private int position;
    private String identificator;

    public Item(int position, ItemStack is, Event event, boolean canceled, boolean closingInventory){
        this.is = is;
        this.event = event;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.position = position;
    }

    public Item(ItemStack is, Event event, boolean canceled, boolean closingInventory){
        this.is = is;
        this.event = event;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
    }

    public Item(String identificator, int position, ItemStack is, Event event, boolean canceled, boolean closingInventory){
        this.is = is;
        this.event = event;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.position = position;
        this.identificator = identificator;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isClosingInventory() {
        return closingInventory;
    }

    public ItemStack getIs() {
        return is;
    }

    public Event getEvent() {
        return event;
    }

    public void setIs(ItemStack is) {
        this.is = is;
    }

    public int getPosition() {
        return position;
    }

    public String getIdentificator() {
        return identificator;
    }
}
