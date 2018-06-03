package cz.HackerGamingCZ.HackerTools.builder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.PlayerAction;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.items.ItemInteractManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemBuilder implements Registrable {

    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private int position = -1;
    private ArrayList<ItemProperty> properties = new ArrayList<>();
    private PlayerAction playerAction;

    public ItemBuilder setPlayerAction(PlayerAction playerAction) {
        register();
        this.playerAction = playerAction;
        return this;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public ItemBuilder addProperty(ItemProperty property) {
        properties.add(property);
        register();
        return this;
    }

    public ItemBuilder removeProperty(ItemProperty property) {
        properties.remove(property);
        return this;
    }

    public ArrayList<ItemProperty> getProperties() {
        return properties;
    }

    public ItemBuilder(Material material) {
        this(material, 1, (byte) 0);
    }

    public ItemBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    public ItemBuilder(Material material, int amount, byte data) {
        itemStack = new ItemStack(material, amount, data);
        itemMeta = itemStack.getItemMeta();
    }

    public int getPosition() {
        return position;
    }

    public ItemBuilder setPosition(int position) {
        this.position = position;
        return this;
    }

    public ItemBuilder(Material material, byte data) {
        this(material, 1, data);
    }

    public ItemBuilder setData(byte data) {
        itemStack.setDurability(data);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        getItemMeta().addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        getItemMeta().removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder hideEnchants() {
        getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        getItemMeta().addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        getItemMeta().setDisplayName(displayName);
        return this;
    }

    public ItemBuilder addLore(String text) {
        List<String> itemLores = new ArrayList<>();
        if (getItemMeta().getLore() != null && getItemMeta().getLore().size() > 0) {
            itemLores = getItemMeta().getLore();
        }
        itemLores.add(text);
        getItemMeta().setLore(itemLores);
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        List<String> itemLore = getItemMeta().getLore();
        Collections.addAll(itemLore, lore);
        getItemMeta().setLore(itemLore);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        getItemMeta().setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setLores(String... lores) {
        getItemMeta().setLore(Arrays.asList(lores));
        return this;
    }

    public ItemBuilder removeItemFlags(ItemFlag... itemFlag) {
        getItemMeta().removeItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder setGlowing(boolean glowing) {
        if (glowing) {
            addEnchantment(Enchantment.LURE, 1);
            hideEnchants();
        } else {
            removeEnchantment(Enchantment.LURE);
            hideEnchants();
        }
        return this;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public ItemStack getItemStack() {
        return build();
    }

    public ItemStack build() {
        itemStack.setItemMeta(getItemMeta());
        return itemStack;
    }

    public ItemBuilder giveItem(Inventory inv, int position) {
        inv.setItem(position, getItemStack());
        return this;
    }

    @Override
    public void register() {
        HackerTools.getPlugin().getItemInteractManager().addItem(this);
    }
}
