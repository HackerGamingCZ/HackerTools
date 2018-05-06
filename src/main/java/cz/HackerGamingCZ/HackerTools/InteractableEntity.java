package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class InteractableEntity {

    private String name;
    private EntityType entityType;
    private Action action;
    private Entity entity;

    public InteractableEntity(EntityType entityType, String name, Action action){
        this.name = name;
        this.entityType = entityType;
        this.action = action;
    }

    public InteractableEntity(Entity entity, Action action){
        if(entity == null){
            return;
        }
        if(entity.getCustomName() == null){
            return;
        }
        name = entity.getCustomName();
        entityType = entity.getType();
        this.action = action;
        this.entity = entity;
    }

    public Action getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void spawnEntity(Location location, boolean customNameVisible, boolean AI){
        this.entity = location.getWorld().spawnEntity(location, entityType);
        entity.setCustomNameVisible(customNameVisible);
        entity.setCustomName(name);
        if(entity instanceof LivingEntity){
            ((LivingEntity) entity).setAI(AI);
        }
        if(entity.getType() == EntityType.ARMOR_STAND){
            ArmorStand as = (ArmorStand) entity;
            as.setVisible(false);
            as.setGravity(false);
        }
    }

    public Entity getEntity() {
        return entity;
    }
}
