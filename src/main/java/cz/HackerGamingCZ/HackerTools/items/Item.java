package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.PlayerAction;
import org.bukkit.inventory.ItemStack;

//TODO Item rework
@Deprecated
public class Item {

    private ItemStack is;
    private boolean canceled;
    private boolean closingInventory;
    private int position;
    private String identificator;
    private PlayerAction action;

    public Item(int position, ItemStack is, PlayerAction playerAction, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.position = position;
        this.action = playerAction;
    }

    public Item(ItemStack is, PlayerAction playerAction, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.action = playerAction;
    }

    public Item(String identificator, int position, ItemStack is, PlayerAction playerAction, boolean canceled, boolean closingInventory) {
        this.is = is;
        this.canceled = canceled;
        this.closingInventory = closingInventory;
        this.action = playerAction;
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

    public PlayerAction getAction() {
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
