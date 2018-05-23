package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import org.bukkit.Bukkit;

public class OnlinePlayerCountPlaceholder implements Placeholder{


    @Override
    public String getName() {
        return "ONLINEPLAYERS";
    }

    @Override
    public String getReplacement() {
        return String.valueOf(Bukkit.getOnlinePlayers().size());
    }

}
