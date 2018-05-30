package cz.HackerGamingCZ.HackerTools.placeholders;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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

    @Deprecated
   public String replaceSpecialPlaceholder(String string, Placeholder placeholder, String replacement) {
        string = string.replace(placeholder.getPlaceholder(), replacement);
        return string;
    }

}
