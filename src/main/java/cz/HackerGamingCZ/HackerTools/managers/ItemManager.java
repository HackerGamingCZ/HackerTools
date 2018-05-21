package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.enchant.Enchant;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ItemManager {

    public boolean isItemLegit(ItemStack is, boolean hasItemMeta, String displayName, String[] lore, boolean isEnchanted) {
        boolean hasDisplayName = displayName != null;
        boolean hasLore = lore != null && lore.length != 0;
        if (is == null) {
            return false;
        }
        if ((hasDisplayName || hasItemMeta || hasLore) && !is.hasItemMeta()) {
            return false;
        }
        if (isEnchanted) {
            int enchants = 0;
            enchants += is.getEnchantments().size();
            if (is.hasItemMeta()) {
                enchants += is.getItemMeta().getEnchants().size();
            }
            if (enchants == 0) {
                return false;
            }
        }
        ItemMeta im = is.getItemMeta();
        if (hasDisplayName && (!im.hasDisplayName() && im.getDisplayName().equals(displayName))) {
            return false;
        }
        if (hasLore && (im.getLore().size() == 0 || im.getLore().size() != lore.length)) {
            return false;
        }
        if (hasLore) {
            ArrayList<String> loreList = new ArrayList<>();
            Collections.addAll(loreList, lore);
            for (int i = 0; i < im.getLore().size(); i++) {
                if (!loreList.get(i).equals(im.getLore().get(i))) {
                    return false;
                }
            }

        }
        return true;
    }

    public ItemStack createArmorItem(Material material, int amount, String name, boolean enchanted, byte data, Color color, String... lore) {
        ItemStack is = createItem(material, amount, name, enchanted, data, lore);
        LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
        meta.setColor(color);
        is.setItemMeta(meta);
        return is;
    }

    public ItemStack createHeadItem(Player player, String name, boolean enchanted, String... lore) {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName(name);
        if (enchanted) {

        }
        ArrayList<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, lore);
        meta.setLore(loreList);
        is.setItemMeta(meta);
        return is;
    }

    public ItemStack addEnchant(ItemStack is, Enchant... enchants){
        for(Enchant enchant : enchants){
            if(enchant.getEnchantment().getMaxLevel() < enchant.getLevel()){
                is.addUnsafeEnchantment(enchant.getEnchantment(), enchant.getLevel());
                continue;
            }
            is.addEnchantment(enchant.getEnchantment(), enchant.getLevel());
        }
        return is;
    }

    public ItemStack addEnchant(ItemStack is, Enchantment enchant, int level){
            if(enchant.getMaxLevel() < level) {
                is.addUnsafeEnchantment(enchant, level);
                return is;
            }
        is.addEnchantment(enchant, level);
        return is;
    }

    public boolean isItemLegit(ItemStack is) {
        return is != null;
    }

    public boolean isItemLegit(ItemStack is, String displayName) {
        if (is == null) {
            return false;
        }
        if (!is.hasItemMeta()) {
            return false;
        }
        ItemMeta im = is.getItemMeta();
        return im.hasDisplayName() && im.getDisplayName().equals(displayName);
    }

    public ItemStack createItem(Material material, int amount, String name, boolean enchanted, byte data, String... lore) {
        ItemStack is;

        if (data != -1) {
            is = new ItemStack(material, amount, data);
        } else {
            is = new ItemStack(material, amount);
        }

        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        List<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, lore);
        im.setLore(loreList);
        if (enchanted) {
            im.addEnchant(Enchantment.LUCK, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        is.setItemMeta(im);

        return is;
    }

    public ItemStack createItem(Material material, String name, boolean enchanted, byte data, String... lore) {
        return createItem(material, 1, name, enchanted, data, lore);
    }

    public ItemStack createItem(Material material, int amount, String name, boolean enchanted, byte data, ArrayList<String> lore) {
        ItemStack is;

        if (data != -1) {
            is = new ItemStack(material, amount, data);
        } else {
            is = new ItemStack(material, amount);
        }

        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        if (enchanted) {
            im.addEnchant(Enchantment.LUCK, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        is.setItemMeta(im);

        return is;
    }

    public ItemStack createItem(Material material, String name, boolean enchanted, String... lore) {
        return createItem(material, 1, name, enchanted, (byte) -1, lore);
    }


}
