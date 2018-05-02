package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.GUI;

import java.util.HashMap;

public class EntityInteractAPI {

    private HashMap<String, GUI> entities = new HashMap<>();

    public void addEntity(GUI gui){
        entities.put(gui.getInventory().getTitle(), gui);
    }

    public GUI getEntity(String title){
        return entities.getOrDefault(title, null);
    }

    public void removeEntity(String title){
        entities.remove(title);
    }

    public HashMap<String, GUI> getEntities() {
        return entities;
    }

}
