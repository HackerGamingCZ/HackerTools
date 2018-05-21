package cz.HackerGamingCZ.HackerTools.managers;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class LoggerManager {

    public void log(String text) {
        Bukkit.getLogger().info(text);
    }

    public void logException(String text, Exception exception) {
        Bukkit.getLogger().log(Level.SEVERE, text, exception);
    }

}
