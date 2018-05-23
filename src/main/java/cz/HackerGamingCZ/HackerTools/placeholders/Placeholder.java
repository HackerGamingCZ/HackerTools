package cz.HackerGamingCZ.HackerTools.placeholders;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface Placeholder {

    public String getName();
    public String getReplacement();
    default String getReplacement(Player player){
        return getReplacement();
    }
    default String getReplacement(OfflinePlayer player){
        return getReplacement();
    }


    default String getPlaceholder(){
        return "[_"+getName()+"_]";
    }

}