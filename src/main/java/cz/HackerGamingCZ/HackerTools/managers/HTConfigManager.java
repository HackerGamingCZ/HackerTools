package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.config.SimpleConfig;
import cz.HackerGamingCZ.HackerTools.config.SimpleConfigManager;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;

public class HTConfigManager {

    private SimpleConfig lang;
    private SimpleConfig config;

    public HTConfigManager() {
        SimpleConfigManager manager = HackerTools.getPlugin().getSimpleConfigManager();
        lang = manager.getNewConfig("lang.yml");
        lang.check("debug-doesnt-exist", Placeholders.DEBUGPREFIX + "This debug argument doesn't exist. Please, contact developer.", "Message to player when debug argument doesn't exist");
        lang.check("default-admin-global-message", "&c" + Placeholders.PLAYERNAME + " &7joined the game in &csetup mode&7. &7(&c" + Placeholders.ONLINEPLAYERS + "&7/&c" + Placeholders.MAXPLAYERS + "&7)");
        lang.check("default-admin-player-message", "&c&lWARNING! &7This game is not prepared! This is mostly caused when developer didn't setup this game using &c" + Placeholders.PLUGINNAME + "&7.");
        lang.check("default-player-global-message", "&c" + Placeholders.PLAYERNAME + " &7joined the game&c&7. &7(&c" + Placeholders.ONLINEPLAYERS + "&7/&c" + Placeholders.MAXPLAYERS + "&7)");
        lang.check("default-player-player-message", null);
        lang.check("default-spectator-global-message", "&c" + Placeholders.PLAYERNAME + " &7joined the game as a &cspectator.");
        lang.check("default-spectator-player-message", "&cThis game is still in progress. You joined as a spectator.");
        lang.check("default-reconnect-global-message", null);
        lang.check("default-reconnect-player-message", "&cYou reconnected to the game!");
        lang.check("default-nobody-global-message", null);
        lang.check("default-nobody-player-message", null);
        lang.check("jointype-nobody-kick-message", "&cThis server doesn't allow players to join.");
        lang.check("jointype-permission-kick-message", "&cYou don't have enough permissions to join this server.");
        lang.check("countdown-start-info", "&7There's enough players to start!");
        lang.check("countdown-info", "&7Game is starting in &c" + Placeholders.COUNTDOWN + "&7 seconds!");
        lang.check("countdown-stop-playerdisconnect-info", "&7We don't have enough players to continue in starting.");
        lang.check("min-players-info", "&7We need at least &c" + Placeholders.MINPLAYERS + " &7players to start");
        lang.check("game-start-info", "&7The game has just begun!");
        lang.check("lobby-disconnect-info", "&c" + Placeholders.PLAYERNAME + " &7left the game&c&7. &7(&c" + Placeholders.ONLINEPLAYERS + "&7/&c" + Placeholders.MAXPLAYERS + "&7)");
        lang.check("argument-wasnt-number", "&c&lERROR &7Argument wasn't number");
        lang.check("teleport-to-player", "&cClick to teleport to &6" + Placeholders.PLAYERNAME);


        config = manager.getNewConfig("config.yml");
        config.check("minigame", true, "Is server minigame?");
        config.check("distributor", "!", "Distributor of shout message");

        saveConfigs();
    }

    public SimpleConfig getLang() {
        return lang;
    }

    public void saveConfigs() {
        lang.saveConfig();
        config.saveConfig();
    }

    public void reloadConfigs() {
        lang.reloadConfig();
    }

    public SimpleConfig getConfig() {
        return config;
    }
}
