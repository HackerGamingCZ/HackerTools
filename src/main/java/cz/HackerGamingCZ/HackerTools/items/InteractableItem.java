package cz.HackerGamingCZ.HackerTools.items;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InteractableItem implements Registrable {

    private Item item;
    private boolean drop;

    public InteractableItem(Material material, int amount, String name, boolean enchanted, byte data, Action action, boolean drop, String... lore) {
        this.item = new Item(HackerTools.getPlugin().getItemManager().createItem(material, amount, name, enchanted, data, lore), action, false, false);
        this.drop = drop;
    }

    public InteractableItem(Material material, int amount, String name, boolean enchanted, byte data, Action action, boolean drop, boolean canceled, boolean closingInventory, String... lore) {
        this.item = new Item(HackerTools.getPlugin().getItemManager().createItem(material, amount, name, enchanted, data, lore), action, canceled, closingInventory);
        this.drop = drop;
    }

    public void giveItem(Inventory inv, int position) {
        if (inv.getSize() < position) {
            return;
        }
        inv.setItem(position, item.getIs());
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void register() {
        HackerTools.getPlugin().getItemInteractManager().addItem(this);
    }

    public boolean isDrop() {
        return drop;
    }
}
