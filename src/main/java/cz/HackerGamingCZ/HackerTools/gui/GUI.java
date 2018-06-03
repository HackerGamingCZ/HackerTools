package cz.HackerGamingCZ.HackerTools.gui;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import cz.HackerGamingCZ.HackerTools.builder.ItemProperty;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
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
        for (ItemBuilder item : getItems(player)) {
            if (item.getPosition() == -1) {
                inv.setItem(inv.firstEmpty(), item.build());
                continue;
            }
            inv.setItem(item.getPosition(), item.build());

        }
        return inv;
    }

    boolean closeInventoryAfterInteract();

    default boolean updateInventoryAfterInteract() {
        return true;
    }

    int getInventorySize();

    String getInventoryName();

    ArrayList<ItemBuilder> getItems(HTPlayer player);

    default ArrayList<ItemBuilder> getItems(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        return getItems(htPlayer);
    }

    default ItemBuilder getItemByISName(ItemStack is, HTPlayer player) {
        if (!is.getItemMeta().hasDisplayName()) {
            return null;
        }
        for (ItemBuilder item : getItems(player)) {
            if (item.build().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName())) {
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
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setGlowing(true).addProperty(ItemProperty.CANCEL_INTERACT).setDisplayName("§c<< Previous page").build();

    }

    default ItemStack getNextPage() {
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setGlowing(true).addProperty(ItemProperty.CANCEL_INTERACT).setDisplayName("§cNext page >>").build();

    }

    default ItemStack getCurrentPage() {
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setGlowing(true).addProperty(ItemProperty.CANCEL_INTERACT).setDisplayName("§cCurrent page").build();
    }

    default ItemStack getBorderType() {
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setGlowing(true).addProperty(ItemProperty.CANCEL_INTERACT).setDisplayName("§c").build();
    }

}
