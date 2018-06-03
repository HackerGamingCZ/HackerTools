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

    private LeatherArmorMeta armorMeta = (LeatherArmorMeta) super.getItemMeta();

    public LeatherArmorBuilder(Material material) {
        this(material, 1, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    public LeatherArmorBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
        Material[] allowedMaterials1 = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET};
        List allowedMaterials = Arrays.asList(allowedMaterials1);

    }

    public LeatherArmorBuilder(Material material, byte data) {
        this(material, 1, data);
    }

    public LeatherArmorBuilder setColor(Color color) {
        getItemMeta().setColor(color);
        return this;
    }

    @Override
    public LeatherArmorMeta getItemMeta() {
        return armorMeta;
    }
}
