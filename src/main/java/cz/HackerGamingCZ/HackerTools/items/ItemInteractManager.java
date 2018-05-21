package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.items.InteractableItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemInteractManager {

    private HashMap<String, InteractableItem> items = new HashMap<>();

    public void addItem(InteractableItem item) {
        items.put(item.getItem().getIs().getItemMeta().getDisplayName(), item);
    }

    public InteractableItem getItemByIs(ItemStack is) {
        if (!is.getItemMeta().hasDisplayName()) {
            return null;
        }
        String name = is.getItemMeta().getDisplayName();
        return items.getOrDefault(name, null);
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public HashMap<String, InteractableItem> getItems() {
        return items;
    }
}
