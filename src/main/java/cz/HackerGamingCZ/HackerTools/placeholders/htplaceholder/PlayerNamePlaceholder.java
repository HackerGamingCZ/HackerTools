package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

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
        if (player == null) {
            return null;
        }
        return player.getName();
    }

    @Override
    public String getReplacement(CommandSender sender) {
        if (sender == null) {
            return null;
        }
        return sender.getName();
    }

    @Override
    public String getReplacement(OfflinePlayer player) {
        if (player == null) {
            return null;
        }
        return player.getName();
    }
}
