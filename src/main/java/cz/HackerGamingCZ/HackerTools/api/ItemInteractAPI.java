package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.InteractableEntity;
import cz.HackerGamingCZ.HackerTools.InteractableItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemInteractAPI {

    private HashMap<String, InteractableItem> items = new HashMap<>();

    public void addItem(String identificator, InteractableItem item){
        items.put(identificator, item);
    }

    public InteractableItem getItemByIdentificator(String name){
        return items.getOrDefault(name, null);
    }
    public InteractableItem getItemByIs(ItemStack is){
        if(!is.getItemMeta().hasDisplayName()){
            return null;
        }
        String name = is.getItemMeta().getDisplayName();
        for(InteractableItem item : items.values()){
            if(item.getIs().getItemMeta().getDisplayName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public void removeItem(String name){
        items.remove(name);
    }

    public HashMap<String, InteractableItem> getItems() {
        return items;
    }
}
