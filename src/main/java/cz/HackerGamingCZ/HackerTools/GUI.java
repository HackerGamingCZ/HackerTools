package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GUI {

    private Inventory inventory;
    private ArrayList<Item> items = new ArrayList<>();
    private int lastPosition = -1;

    public GUI(String title, int size){
        inventory = Bukkit.createInventory(null, size, title);
    }

    public GUI(String title, InventoryType type){
        inventory = Bukkit.createInventory(null, type, title);
    }

    public void setItem(int position, Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Event event, boolean canceled, boolean closingInventory){
        Item item = new Item(material, amount, name, lore, enchanted, data, event, canceled, closingInventory, this);
        items.add(item);
        inventory.setItem(position, item.getIs());
        lastPosition = position;
    }

    public void addToNextSlot(Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Event event, boolean cancel, boolean closingInventory){
        lastPosition++;
        setItem(lastPosition, material, amount, name, lore, enchanted, data, event, cancel, closingInventory);
    }

    public void addToNextSlot(Material material, int amount, String name, String[] lore, boolean enchanted, byte data){
        lastPosition++;
        setItem(lastPosition, material, amount, name, lore, enchanted, data, null, false, false);
    }

    public Item getItembyIS(ItemStack is){
        for(Item item : items){
            if(item.getIs().isSimilar(is)){
                return item;
            }
        }
        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void open(Player player){
        player.openInventory(inventory);
    }
}
