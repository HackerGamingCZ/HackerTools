package cz.HackerGamingCZ.HackerTools.gui;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface GUI extends Registrable {

    @Override
    default void register() {
        HackerTools.getPlugin().getGuiManager().addGUI(this);
    }

    default Inventory getInventory(Player player, int page) {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        /*if(getItems(player).size() > getInventorySize()){
            int itemsPerPage = getInventorySize()-18;
            int pages = (int)Math.round((double)getItems(player).size()/itemsPerPage);
        }*/
        for (Item item : getItems(player)) {
            inv.setItem(item.getPosition(), item.getIs());
        }
        return inv;
    }

    int getInventorySize();

    String getInventoryName();

    ArrayList<Item> getItems(HTPlayer player);

    default ArrayList<Item> getItems(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        return getItems(htPlayer);
    }

    default Item getItemByISName(ItemStack is, HTPlayer player) {
        if (!is.getItemMeta().hasDisplayName()) {
            return null;
        }
        for (Item item : getItems(player)) {
            if (item.getIs().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName())) {
                return item;
            }
        }
        return null;
    }

    default void open(Player player, int page) {
        player.openInventory(getInventory(player, page));
    }

    default void open(HTPlayer player, int page) {
        open(player.getPlayer(), page);
    }

    default ItemStack getPreviousPage() {
        return HackerTools.getPlugin().getItemManager().createItem(Material.STAINED_GLASS_PANE, 1, "§c<< Previous page", false, (byte) 5);

    }

    default ItemStack getNextPage() {
        return HackerTools.getPlugin().getItemManager().createItem(Material.STAINED_GLASS_PANE, 1, "§cNext page >>", false, (byte) 5);

    }

    default ItemStack getCurrentPage() {
        return HackerTools.getPlugin().getItemManager().createItem(Material.STAINED_GLASS_PANE, 1, "§cCurrent page", false, (byte) 5);
    }

    default ItemStack getBorderType() {
        return HackerTools.getPlugin().getItemManager().createItem(Material.STAINED_GLASS_PANE, 1, "§c", false, (byte) 5);
    }

}
