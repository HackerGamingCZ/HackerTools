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
    public static String PUNISHMENT_PREFIX = "§4§lPUNISHMENT §8>>§7 ";

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
    public static String HT_COMMAND_ARGUMENT_DOESNT_EXIST = Placeholders.ERRORPREFIX + "This argument does not exist. Try §c/ht help§7.";
    public static String SENDER_MUST_BE_PLAYER = Placeholders.CONSOLEERROR + "You must be player!";
    public static String UNKNOWN_ERROR = Placeholders.ERRORPREFIX + "Unknown error.";
    public static String TOO_BIG_NUMBER = Placeholders.ERRORPREFIX + "Number was too big.";
    public static String SUCCESSFULY_SET_SPEED = Placeholders.HTPREFIX + "Successfully set your speed.";
    public static String FORCESTART_EXECUTED = Placeholders.HTPREFIX + "Starting the game...";
    public static String PLAYER_NOT_FOUND = Placeholders.ERRORPREFIX + "Players wasn't found.";
    public static String SUCCESSFULY_KICKED_PLAYER = Placeholders.PUNISHMENTPREFIX + "You have successfully kicked a player.";
    public static String NO_ENTITIES_FOUND = Placeholders.ERRORPREFIX + "No entities found. :(";
    public static String PLUGIN_RELOADED = Placeholders.HTPREFIX + "Plugin §e" + Placeholders.PLUGINNAME + " §7reloaded!";
    public static String SUCCESSFULY_KICKED_ALL_PLAYERS = Placeholders.PUNISHMENTPREFIX + "You have kicked §call players §7except those with §cbypass §7permission!";
    public static String WRONG_ARGUMENT = Placeholders.ERRORPREFIX + "Sorry, I don't know that argument. :(";
    public static String PLAYER_HAS_KICK_PROTECTION = Placeholders.ERRORPREFIX + "Sorry, but this player has protection. If you really want to kick him, add §c\"-op\" §7modificator.";

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