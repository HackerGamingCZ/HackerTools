package cz.HackerGamingCZ.HackerTools.placeholders;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class PlaceholderManager {

    public String replaceString(String string, Player player) {
        return replaceString(string, player, new Placeholders[]{});
    }

    public String replaceString(String string, OfflinePlayer player) {
        return replaceString(string, player, new Placeholders[]{});
    }

    public String replaceString(String string) {
        return replaceString(string, null);
    }

    public String replaceString(String string, Player player, Placeholders... ignore) {
        return replaceString(string, (CommandSender) player, ignore);
    }

    public String replaceString(String string, HTPlayer player, Placeholders... ignore) {
        return replaceString(string, player.getPlayer(), ignore);
    }

    public String replaceString(String string, CommandSender sender, Placeholders... ignore) {
        if (string == null) {
            return null;
        }
        ArrayList<Placeholders> ignoreList = new ArrayList<>();
        Collections.addAll(ignoreList, ignore);
        for (Placeholders placeholder : Placeholders.values()) {
            if (ignoreList.contains(placeholder)) {
                continue;
            }
            if (sender == null) {
                if (placeholder.getPlaceholder().getPlaceholder() == null) {
                    continue;
                }
                if (placeholder.getPlaceholder().getReplacement() == null) {
                    continue;
                }
                string = string.replace(placeholder.getPlaceholder().getPlaceholder(), placeholder.getPlaceholder().getReplacement());
                continue;
            }
            string = string.replace(placeholder.getPlaceholder().getPlaceholder(), placeholder.getPlaceholder().getReplacement(sender));
        }
        return string;
    }

    public String replaceString(String string, OfflinePlayer player, Placeholders... ignore) {
        if (string == null) {
            return null;
        }
        ArrayList<Placeholders> ignoreList = new ArrayList<>();
        Collections.addAll(ignoreList, ignore);
        for (Placeholders placeholder : Placeholders.values()) {
            if (ignoreList.contains(placeholder)) {
                continue;
            }
            string = string.replace(placeholder.getPlaceholder().getPlaceholder(), placeholder.getPlaceholder().getReplacement(player));
        }
        return string;
    }

}
