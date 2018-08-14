package cz.HackerGamingCZ.HackerTools.builder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;


public class LeatherArmorBuilder extends ItemBuilder {

    private LeatherArmorMeta armorMeta = (LeatherArmorMeta) super.getItemMeta();

    public LeatherArmorBuilder(LeatherArmorMaterial material) {
        this(material, 1, (byte) 0);
    }

    public LeatherArmorBuilder(LeatherArmorMaterial material, int amount) {
        this(material, amount, (byte) 0);
    }

    public LeatherArmorBuilder(LeatherArmorMaterial material, int amount, byte data) {
        super(material.getBukkitMaterial(), amount, data);
        Material[] allowedMaterials1 = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET};
        List allowedMaterials = Arrays.asList(allowedMaterials1);

    }

    public LeatherArmorBuilder(LeatherArmorMaterial material, byte data) {
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

    public enum LeatherArmorMaterial {

        HELMET(Material.LEATHER_HELMET),
        CHESTPLATE(Material.LEATHER_CHESTPLATE),
        LEGGINGS(Material.LEATHER_LEGGINGS),
        BOOTS(Material.LEATHER_BOOTS);

        private Material bukkitMaterial;

        LeatherArmorMaterial(Material bukkitMaterial) {
            this.bukkitMaterial = bukkitMaterial;
        }

        public Material getBukkitMaterial() {
            return bukkitMaterial;
        }
    }
}
