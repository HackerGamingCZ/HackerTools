package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.GUI;
import cz.HackerGamingCZ.HackerTools.InteractableEntity;

import java.util.HashMap;

public class EntityInteractAPI {

    private HashMap<String, InteractableEntity> entities = new HashMap<>();

    public void addEntity(InteractableEntity entity){
        entities.put(entity.getName(), entity);
    }

    public InteractableEntity getEntity(String title){
        return entities.getOrDefault(title, null);
    }

    public void removeEntity(String title){
        entities.remove(title);
    }

    public HashMap<String, InteractableEntity> getEntities() {
        return entities;
    }

}
