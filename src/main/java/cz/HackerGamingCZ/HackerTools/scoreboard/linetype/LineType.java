package cz.HackerGamingCZ.HackerTools.scoreboard.linetype;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface LineType {

    default String getText(Player player) {
        return getText();
    }

    default String getText(OfflinePlayer player) {
        return getText();
    }

    String getText();

    default String getText(HTPlayer player) {
        return getText();
    }

}
