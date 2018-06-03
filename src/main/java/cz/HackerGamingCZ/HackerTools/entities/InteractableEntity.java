package cz.HackerGamingCZ.HackerTools.entities;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.PlayerAction;
import cz.HackerGamingCZ.HackerTools.Registrable;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class InteractableEntity implements Registrable {

    private Entity entity;
    private ArrayList<Entity> entities = new ArrayList<>();
    private EntityType entityType;
    private String customName;
    private boolean customNameVisible;
    private PlayerAction action;
    private int id;

    protected InteractableEntity(EntityType entityType, String customName, boolean customNameVisible, PlayerAction action) {
        this.entityType = entityType;
        this.customName = customName;
        this.customNameVisible = customNameVisible;
        this.action = action;
        this.id = HackerTools.getPlugin().getEntityInteractManager().getNextId();
        register();
    }

    public int getId() {
        return id;
    }

    public void despawn() {
        for (Entity entity : entities) {
            entity.remove();
        }
    }

    public void spawn(Location location) {
    }

    public PlayerAction getAction() {
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

    @Override
    public void register() {
        HackerTools.getPlugin().getEntityInteractManager().addEntity(this);
    }
}
