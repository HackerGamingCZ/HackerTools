package cz.HackerGamingCZ.HackerTools.entities;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class InteractableLivingEntity extends InteractableEntity {

    private boolean AI;

    public InteractableLivingEntity(EntityType entityType, String customName, boolean customNameVisible, Action action, boolean AI){
        super(entityType, customName, customNameVisible, action);
        this.AI = AI;
    }

    @Override
    public void spawn(Location location) {
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, getEntityType());
        entity.setCustomName(getCustomName());
        entity.setCustomNameVisible(isCustomNameVisible());
        entity.setAI(isAI());
        setEntity(entity);
    }

    public boolean isAI() {
        return AI;
    }
}
