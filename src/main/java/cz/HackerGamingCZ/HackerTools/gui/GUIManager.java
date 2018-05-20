package cz.HackerGamingCZ.HackerTools.gui;

import java.util.HashMap;

public class GUIManager {

    private HashMap<String, GUI> guis = new HashMap<>();

    public void addGUI(GUI gui){
        if(gui.getInventory(null) == null){
            return;
        }
        guis.put(gui.getInventoryName(), gui);
    }

    public GUI getGUI(String name){
        return guis.getOrDefault(name, null);
    }

    public void removeGUI(String name){
        guis.remove(name);
    }

}
