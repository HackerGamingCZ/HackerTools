package cz.HackerGamingCZ.HackerTools.builder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LeatherArmorBuilder extends ItemBuilder {

    LeatherArmorMeta armorMeta = (LeatherArmorMeta) super.getItemMeta();

    private Material[] allowedMaterials = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET};

    public LeatherArmorBuilder(Material material) {
        this(material, 1, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
        List allowedMaterials = Arrays.asList(this.allowedMaterials);

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
