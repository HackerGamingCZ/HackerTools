package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemInteractManager {

    private HashMap<String, ItemBuilder> items = new HashMap<>();

    public void addItem(ItemBuilder item) {
        if (!item.build().getItemMeta().hasDisplayName()) {
            return;
        }
        items.put(item.build().getItemMeta().getDisplayName(), item);
    }

    public ItemBuilder getItemByIs(ItemStack is) {
        for (ItemBuilder item : items.values()) {
            if (item.getItemStack().isSimilar(is)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public HashMap<String, ItemBuilder> getItems() {
        return items;
    }
}
