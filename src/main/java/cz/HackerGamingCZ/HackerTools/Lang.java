package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.config.SimpleConfig;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import org.bukkit.entity.Player;

public class Lang {

    //PREFIXES
    public static String DEBUG_PREFIX = "§5§lDEBUG §8>>§7 ";
    public static String HACKERTOOLS_PREFIX = "§6§lHackerTools §8>> §7";
    public static String ERROR_PREFIX = "§4§lERROR §8>>§7 ";
    public static String CONSOLE_ERROR = "ERROR >> ";

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
    public static String NOT_ENOUGH_ARGUMENTS;
    public static String HT_COMMAND_ARGUMENT_DOESNT_EXIST = Placeholders.ERRORPREFIX + "this argument does not exist. Try §c/ht help§7.";
    public static String SENDER_MUST_BE_PLAYER = Placeholders.CONSOLEERROR + "you must be player!";
    public static String UNKNOWN_ERROR = Placeholders.ERRORPREFIX + "unknown error.";
    public static String TOO_BIG_NUMBER = Placeholders.ERRORPREFIX + "number was too big.";
    public static String SUCCESSFULY_SET_SPEED = Placeholders.HTPREFIX + "successfully set your speed.";
    public static String FORCESTART_EXECUTED = Placeholders.HTPREFIX + "Starting the game...";

    public static String get(String string, Player player) {
        return HackerTools.getPlugin().getPlaceholderManager().replaceString(string, player);
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
        NOT_ENOUGH_ARGUMENTS = config.getString("not-enough-arguments");
    }

}