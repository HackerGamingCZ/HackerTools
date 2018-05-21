package cz.HackerGamingCZ.HackerTools.entities;

import cz.HackerGamingCZ.HackerTools.actions.Action;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class InteractableEntity {

    private Entity entity;
    private ArrayList<Entity> entities = new ArrayList<>();
    private EntityType entityType;
    private String customName;
    private boolean customNameVisible;
    private Action action;

    protected InteractableEntity(EntityType entityType, String customName, boolean customNameVisible, Action action) {
        this.entityType = entityType;
        this.customName = customName;
        this.customNameVisible = customNameVisible;
        this.action = action;
    }

    public void despawn() {
        entity.remove();
    }

    public void spawn(Location location) {
    }

    public Action getAction() {
        return action;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public String getCustomName() {
        return customName;
    }

    public boolean isCustomNameVisible() {
        return customNameVisible;
    }

    public void setCustomName(String customName) {
        entity.setCustomName(customName);
        this.customName = customName;
        for (Entity entity : entities) {
            entity.setCustomName(customName);
        }
    }

    public void setCustomNameVisible(boolean customNameVisible) {
        entity.setCustomNameVisible(customNameVisible);
        this.customNameVisible = customNameVisible;
        for (Entity entity : entities) {
            entity.setCustomNameVisible(customNameVisible);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        entities.add(entity);
    }
}
