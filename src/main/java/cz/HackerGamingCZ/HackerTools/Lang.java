package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.enums.Placeholder;

public class Lang {

    //PREFIXES
    public static String DEBUG_PREFIX = "§8[§c§lHT_DEBUG§8]§7 ";
    public static String HACKERTOOLS_PREFIX = "§8[§c§lHackerTools§8]§7 ";


    public static String DEBUG_DOESNT_EXIST = "[_DEBUGPREFIX_]This debug argument doesn't exist. Please, contact developer.";
    public static String DEFAULT_SETUP_GLOBAL_MESSAGE = "§c"+ Placeholder.PLAYERNAME+" §7joined the game in §csetup mode§7. §7(§c"+ Placeholder.ONLINEPLAYERS+"§7/§c"+ Placeholder.MAXPLAYERS+"§7)";
    public static String DEFAULT_SETUP_PLAYER_MESSAGE = "§c§lWARNING! §7This game is not prepared! This is mostly caused when developer didn't setup this game using §c"+Placeholder.PLUGINNAME+"§7.";
    public static String DEFAULT_WAITING_GLOBAL_MESSAGE = "§c"+ Placeholder.PLAYERNAME+" §7joined the game§c§7. §7(§c"+ Placeholder.ONLINEPLAYERS+"§7/§c"+ Placeholder.MAXPLAYERS+"§7)";
    public static String DEFAULT_WAITING_PLAYER_MESSAGE = null;
    public static String DEFAULT_STARTING_GLOBAL_MESSAGE = "§c"+ Placeholder.PLAYERNAME+" §7joined the game. §7(§c"+ Placeholder.ONLINEPLAYERS+"§7/§c"+ Placeholder.MAXPLAYERS+"§7)";
    public static String DEFAULT_STARTING_PLAYER_MESSAGE = null;
    public static String DEFAULT_INGAME_GLOBAL_MESSAGE = "§c"+ Placeholder.PLAYERNAME+" §7joined the game as a §cspectator.";
    public static String DEFAULT_INGAME_PLAYER_MESSAGE = "§cThis game is still in progress. You joined as a spectator.";
    public static String DEFAULT_END_GLOBAL_MESSAGE = null;
    public static String DEFAULT_END_PLAYER_MESSAGE = null;
    public static String DEFAULT_RESET_GLOBAL_MESSAGE = null;
    public static String DEFAULT_RESET_PLAYER_MESSAGE = null;
    public static String JOINTYPE_NOBODY_KICK_MESSAGE = "§cThis server doesn't allow players to join.";
    public static String JOINTYPE_PERMISSION_KICK_MESSAGE = "§cYou don't have enough permissions to join this server.";
    public static String COUNTDOWN_START_INFO = "§7There's enough players to start!";
    public static String COUNTDOWN_INFO = "§7Game is starting in §c"+Placeholder.COUNTDOWN+"§7 seconds!";
    public static String COUNTDOWN_STOP_PLAYERDISCONNECT_INFO = "§7We don't have enough players to continue in starting.";
    public static String MIN_PLAYERS_INFO = "§7We need at least §c"+Placeholder.MINPLAYERS+" §7players to start.";
    public static String GAME_START_INFO = "§7The game has just begun!";
    public static String LOBBY_DISCONNECT_INFO = "§c"+ Placeholder.PLAYERNAME+" §7left the game§c§7. §7(§c"+ Placeholder.ONLINEPLAYERS+"§7/§c"+ Placeholder.MAXPLAYERS+"§7)";


    public static String get(String string){
        return HackerTools.getPlugin().getPlaceholderAPI().replaceString(string);
    }

}