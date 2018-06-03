package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class LoggerManager {

    public void log(String text) {
        text = HackerTools.getPlugin().getPlaceholderManager().replaceString(text, Bukkit.getOfflinePlayer("CONSOLE"));
        Bukkit.getLogger().info(text);
    }

    public void warn(String text) {
        text = HackerTools.getPlugin().getPlaceholderManager().replaceString(text, Bukkit.getOfflinePlayer("CONSOLE"));
        Bukkit.getLogger().warning(text);
    }

    public void logException(String text, Exception exception) {
        text = HackerTools.getPlugin().getPlaceholderManager().replaceString(text, Bukkit.getOfflinePlayer("CONSOLE"));
        Bukkit.getLogger().log(Level.SEVERE, text, exception);
    }

}
