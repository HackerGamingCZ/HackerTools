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
import java.util.HashMap;

public interface GUI extends Registrable {

    @Override
    default void register() {
        HackerTools.getPlugin().getGuiManager().addGUI(this);
    }

    default Inventory getInventory(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        for (int i : getItems(player).keySet()) {
            ItemBuilder item = getItems(player).get(i);
            inv.setItem(i, item.build());

        }
        return inv;
    }

    boolean closeInventoryAfterInteract();

    default boolean updateInventoryAfterInteract() {
        return true;
    }

    int getInventorySize();

    String getInventoryName();

    HashMap<Integer, ItemBuilder> getItems(HTPlayer player);

    default HashMap<Integer, ItemBuilder> getItems(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        return getItems(htPlayer);
    }

    default ItemBuilder getItemByISName(ItemStack is, HTPlayer player) {
        if (!is.getItemMeta().hasDisplayName()) {
            return null;
        }
        for (ItemBuilder item : getItems(player).values()) {
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
