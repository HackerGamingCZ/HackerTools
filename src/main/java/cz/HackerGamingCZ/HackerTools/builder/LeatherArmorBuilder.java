package cz.HackerGamingCZ.HackerTools.builder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


public class LeatherArmorBuilder extends ItemBuilder {

    LeatherArmorMeta armorMeta = (LeatherArmorMeta) getItemMeta();

    public LeatherArmorBuilder(Material material) {
        this(material, 1, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
    }

    public LeatherArmorBuilder(Material material, byte data) {
        this(material, 1, data);
    }

    public LeatherArmorBuilder setColor(Color color) {
        armorMeta.setColor(color);
        return this;
    }

    @Override
    public ItemMeta getItemMeta() {
        return armorMeta;
    }
}
