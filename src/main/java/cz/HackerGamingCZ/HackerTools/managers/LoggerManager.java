package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class LoggerManager {

    public void log(String text) {
        Bukkit.getLogger().info(text);
    }

    public void warn(String text) {
        Bukkit.getLogger().warning(text);
    }

    public void logException(String text, Exception exception) {
        Bukkit.getLogger().log(Level.SEVERE, text, exception);
    }

}
