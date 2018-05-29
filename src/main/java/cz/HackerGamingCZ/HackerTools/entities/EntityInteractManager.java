package cz.HackerGamingCZ.HackerTools.entities;

import java.util.HashMap;

public class EntityInteractManager {

    private HashMap<String, InteractableEntity> entities = new HashMap<>();

    public void addEntity(InteractableEntity entity) {
        entities.put(entity.getCustomName(), entity);
    }

    public InteractableEntity getEntity(String title) {
        return entities.getOrDefault(title, null);
    }

    public void removeEntity(String title) {
        entities.remove(title);
    }

    public HashMap<String, InteractableEntity> getEntities() {
        return entities;
    }

}
