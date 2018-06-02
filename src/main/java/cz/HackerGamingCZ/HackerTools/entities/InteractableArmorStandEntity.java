package cz.HackerGamingCZ.HackerTools.entities;

import cz.HackerGamingCZ.HackerTools.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class InteractableArmorStandEntity extends InteractableEntity {

    private boolean gravity;
    private boolean visible;
    private boolean small;

    public InteractableArmorStandEntity(String customName, boolean customNameVisible, PlayerAction action, boolean gravity, boolean visible, boolean small) {
        super(EntityType.ARMOR_STAND, customName, customNameVisible, action);
        this.gravity = gravity;
        this.visible = visible;
        this.small = small;
    }

    @Override
    public void spawn(Location location) {
        ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(location, getEntityType());
        entity.setCustomName(getCustomName());
        entity.setCustomNameVisible(isCustomNameVisible());
        entity.setGravity(isGravity());
        entity.setVisible(isVisible());
        entity.setSmall(isSmall());
        setEntity(entity);
    }

    public boolean isGravity() {
        return gravity;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isSmall() {
        return small;
    }
}
