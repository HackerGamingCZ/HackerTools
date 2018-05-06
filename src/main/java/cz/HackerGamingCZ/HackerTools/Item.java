package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Item {

    private ItemStack is;
    private Event event;
    private boolean canceled;
    private boolean closingInventory;
    private GUI gui;

    public Item(ItemStack is, Event event, boolean canceled, boolean closingInventory, GUI gui){
        this.is = is;
        this.event = event;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.gui = gui;
    }

    public Item(Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Event event, boolean canceled, boolean closingInventory, GUI gui){
        this.is = HackerTools.getPlugin().getItemManager().createItem(material, amount, name, lore, enchanted, data);
        this.event = event;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.gui = gui;
    }

    public Item(Material material, int amount, String name, String[] lore, boolean enchanted, byte data){
        this.is = HackerTools.getPlugin().getItemManager().createItem(material, amount, name, lore, enchanted, data);
    }
    public Item(Material material, int amount, String name, String[] lore, boolean enchanted, byte data, GUI gui){
        this.is = HackerTools.getPlugin().getItemManager().createItem(material, amount, name, lore, enchanted, data);
        this.gui = gui;
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

    public GUI getGui() {
        return gui;
    }

    public void setIs(ItemStack is) {
        this.is = is;
    }
}
