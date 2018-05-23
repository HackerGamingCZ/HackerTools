package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerNamePlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "PLAYERNAME";
    }

    @Override
    public String getReplacement() {
        return null;
    }

    @Override
    public String getReplacement(Player player) {
        return player.getName();
    }

    @Override
    public String getReplacement(OfflinePlayer player) {
        return player.getName();
    }
}
