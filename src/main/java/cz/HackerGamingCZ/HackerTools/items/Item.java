package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.inventory.ItemStack;

public class Item {

    private ItemStack is;
    private boolean canceled;
    private boolean closingInventory;
    private int position;
    private String identificator;
    private Action action;

    public Item(int position, ItemStack is, Action action, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.position = position;
        this.action = action;
    }

    public Item(ItemStack is, Action action, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.action = action;
    }

    public Item(String identificator, int position, ItemStack is, Action action, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.action = action;
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

    public Action getAction() {
        return action;
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
