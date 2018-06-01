package cz.HackerGamingCZ.HackerTools.placeholders;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Placeholder {

    String getName();
    String getReplacement();

    default String getReplacement(Player player){
        return getReplacement();
    }

    default String getReplacement(CommandSender player) {
        return getReplacement();
    }
    default String getReplacement(OfflinePlayer player){
        return getReplacement();
    }
    default String getReplacement(HTPlayer player){
        return getReplacement();
    }


    default String getPlaceholder(){
        return "[_"+getName()+"_]";
    }

}