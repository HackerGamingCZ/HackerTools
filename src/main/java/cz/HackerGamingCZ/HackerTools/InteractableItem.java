package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InteractableItem {

    private ItemStack is;
    private Action action;

    public InteractableItem(Material material, int amount, String name, String[] lore, boolean enchanted, byte data, Action action){
        this.is = HackerTools.getPlugin().getItemManager().createItem(material, amount, name, lore, enchanted, data);
        this.action = action;
    }
    
    public Action getAction() {
        return action;
    }
    public ItemStack getIs() {
        return is;
    }
    public void giveItem(Inventory inv, int position){
        if(inv.getSize() < position){
            return;
        }
        inv.setItem(position, is);
    }
}
