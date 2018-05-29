package cz.HackerGamingCZ.HackerTools.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material, 1);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount, byte data) {
        itemStack = new ItemStack(material, amount, data);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, byte data) {
        itemStack = new ItemStack(material, 1, data);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder hideEnchants() {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder addLore(String text) {
        List<String> itemLores = itemMeta.getLore();
        itemLores.add(text);
        itemMeta.setLore(itemLores);
        return this;
    }

    public ItemBuilder addLores(String... lore) {
        List<String> itemLore = itemMeta.getLore();
        Collections.addAll(itemLore, lore);
        itemMeta.setLore(itemLore);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setLores(String... lores) {
        itemMeta.setLore(Arrays.asList(lores));
        return this;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public ItemStack getItemStack() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
