package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.GUI;

import java.util.HashMap;

public class GUIAPI {

    private HashMap<String, GUI> guis = new HashMap<>();

    public void addGUI(GUI gui){
        guis.put(gui.getInventory().getTitle(), gui);
    }

    public GUI getGUI(String title){
        return guis.getOrDefault(title, null);
    }

    public void removeGUI(String title){
        guis.remove(title);
    }

    public HashMap<String, GUI> getGuis() {
        return guis;
    }
}
