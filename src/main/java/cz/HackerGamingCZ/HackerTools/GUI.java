package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GUI {

    private Inventory inventory;
    private HashMap<ItemStack, Event> items = new HashMap<>();
    private int lastPosition = -1;

    GUI(String title, int size){
        inventory = Bukkit.createInventory(null, size, title);
    }

    GUI(String title, InventoryType type){
        inventory = Bukkit.createInventory(null, type, title);
    }

    public void setItem(int position, Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Event event){
        ItemStack is = HackerTools.getPlugin().getItemManager().createItem(material, amount, name, lore, enchanted, data);
        items.put(is, event);
        inventory.setItem(position, is);
        lastPosition = position;
    }

    public void addToNextSlot(Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Event event){
        lastPosition++;
        setItem(lastPosition, material, amount, name, lore, enchanted, data, event);
    }

    public Event getItemsEvent(ItemStack is){
        return items.getOrDefault(is, null);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void open(Player player){
        player.openInventory(inventory);
    }
}
