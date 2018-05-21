package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.config.SimpleConfig;

public class Lang {


    //PREFIXES
    public static String DEBUG_PREFIX = "§8[§c§lHT_DEBUG§8]§7 ";
    public static String HACKERTOOLS_PREFIX = "§8[§c§lHackerTools§8]§7 ";
    public static String ERROR_PREFIX = "§c§lERROR§7 ";

    public static String DEBUG_DOESNT_EXIST;
    public static String JOINTYPE_NOBODY_KICK_MESSAGE;
    public static String JOINTYPE_PERMISSION_KICK_MESSAGE;
    public static String COUNTDOWN_START_INFO;
    public static String COUNTDOWN_INFO;
    public static String COUNTDOWN_STOP_PLAYERDISCONNECT_INFO;
    public static String MIN_PLAYERS_INFO;
    public static String GAME_START_INFO;
    public static String LOBBY_DISCONNECT_INFO;
    public static String ARGUMENT_WASNT_NUMBER;
    public static String TELEPORT_TO_PLAYER;

    public static String get(String string) {
        return HackerTools.getPlugin().getPlaceholderAPI().replaceString(string);
    }

    public static void load() {
        SimpleConfig config = HackerTools.getPlugin().getHtConfigManager().getLang();
        DEBUG_DOESNT_EXIST = config.getString("debug-doesnt-exist");
        JOINTYPE_NOBODY_KICK_MESSAGE = config.getString("jointype-nobody-kick-message");
        JOINTYPE_PERMISSION_KICK_MESSAGE = config.getString("jointype-permission-kick-message");
        COUNTDOWN_START_INFO = config.getString("countdown-start-info");
        COUNTDOWN_INFO = config.getString("countdown-info");
        COUNTDOWN_STOP_PLAYERDISCONNECT_INFO = config.getString("countdown-stop-playerdisconnect-info");
        MIN_PLAYERS_INFO = config.getString("min-players-info");
        GAME_START_INFO = config.getString("game-start-info");
        LOBBY_DISCONNECT_INFO = config.getString("lobby-disconnect-info");
        ARGUMENT_WASNT_NUMBER = config.getString("argument-wasnt-number");
        TELEPORT_TO_PLAYER = config.getString("teleport-to-player");
    }

}