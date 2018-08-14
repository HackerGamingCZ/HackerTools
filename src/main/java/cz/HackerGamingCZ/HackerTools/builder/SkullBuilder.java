package cz.HackerGamingCZ.HackerTools.builder;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder extends ItemBuilder {

    private SkullMeta skullMeta = (SkullMeta) super.getItemMeta();

    public SkullBuilder() {
        super(Material.SKULL_ITEM);
    }

    public SkullBuilder setOwner(String name) {
        getItemMeta().setOwner(name);
        return this;
    }

    public SkullBuilder setOwner(OfflinePlayer player) {
        getItemMeta().setOwningPlayer(player);
        return this;
    }

    @Override
    public SkullMeta getItemMeta() {
        return skullMeta;
    }
}
