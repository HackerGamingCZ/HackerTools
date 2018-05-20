package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.events.Event;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InteractableItem {

    private Item item;
    private boolean drop;

    public InteractableItem(Material material, int amount, String name, boolean enchanted, byte data, Event event, boolean drop, String... lore){
        this.item = new Item(HackerTools.getPlugin().getItemManager().createItem(material, amount, name, enchanted, data, lore), event, false, false);
        this.drop = drop;
    }

    public InteractableItem(Material material, int amount, String name, boolean enchanted, byte data, Event event, boolean drop, boolean canceled, boolean closingInventory, String... lore){
        this.item = new Item(HackerTools.getPlugin().getItemManager().createItem(material, amount, name, enchanted, data, lore), event, canceled, closingInventory);
        this.drop = drop;
    }

    public void giveItem(Inventory inv, int position){
        if(inv.getSize() < position){
            return;
        }
        inv.setItem(position, item.getIs());
    }

    public Item getItem() {
        return item;
    }

    public void register(){
        HackerTools.getPlugin().getItemInteractManager().addItem(this);
    }

    public boolean isDrop() {
        return drop;
    }
}
