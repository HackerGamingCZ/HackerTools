package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import cz.HackerGamingCZ.HackerTools.managers.ChatManager;
import cz.HackerGamingCZ.HackerTools.debug.DebugCommand;
import cz.HackerGamingCZ.HackerTools.config.SimpleConfigManager;
import cz.HackerGamingCZ.HackerTools.debug.DebugManager;
import cz.HackerGamingCZ.HackerTools.entities.InteractableEntity;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.gui.GUIManager;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorPlayerlist;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorSettings;
import cz.HackerGamingCZ.HackerTools.commands.HTCommand;
import cz.HackerGamingCZ.HackerTools.entities.EntityInteractManager;
import cz.HackerGamingCZ.HackerTools.managers.ItemInteractManager;
import cz.HackerGamingCZ.HackerTools.managers.*;
import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import cz.HackerGamingCZ.HackerTools.placeholders.PlaceholderManager;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.players.PlayerManager;
import cz.HackerGamingCZ.HackerTools.teams.TeamManager;
import cz.HackerGamingCZ.HackerTools.teams.htteams.SpectatorTeam;
import cz.HackerGamingCZ.HackerTools.updater.Updater;
import cz.HackerGamingCZ.HackerTools.updater.UpdaterPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HackerTools extends JavaPlugin {

    private static HackerTools plugin;
    private DebugManager debugManager;
    private EventManager eventManager;
    private CommandManager commandManager;
    private PlaceholderManager placeholderManager;
    private ChatManager chatManager;
    private MinigameManager minigameManager;
    private EntityInteractManager entityInteractManager;
    private ItemInteractManager itemInteractManager;
    private SchedulerManager schedulerManager;
    private GUIManager guiManager;
    private TitleManager titleManager;
    private TeamManager teamManager;
    private PlayerManager playerManager;
    private Mechanics mechanics;
    private SimpleConfigManager simpleConfigManager;
    private HTConfigManager htConfigManager;
    private LoggerManager loggerManager;
    private PluginDescriptionFile pdf;
    private ServerManager serverManager;
    private RandomManager randomManager;
    private Updater updater;

    private boolean minigame;

    private ItemBuilder forcestartItem;
    private ItemBuilder spectatorSettingsItem;
    private ItemBuilder spectatorPlayerListItem;

    private GUI spectatorSettings;
    private GUI spectatorPlayerList;

    private SpectatorTeam spectatorTeam;

    private final List<UpdaterPlugin> pluginsToUpdate = new ArrayList<>();

    @Override
    public void onEnable() {
        spectatorSettings = new SpectatorSettings();
        spectatorSettings.register();
        spectatorPlayerList = new SpectatorPlayerlist();
        spectatorPlayerList.register();
        spectatorTeam = new SpectatorTeam();
        spectatorTeam.register();
        forcestartItem = new ItemBuilder(Material.PAPER)
                .setGlowing(true)
                .setDisplayName("§c§lForcestart")
                .addLore("")
                .addLore("§cClick to forcestart the game")
                .setPlayerAction(player -> player.getPlayer().performCommand("ht forcestart"));
        spectatorSettingsItem = new ItemBuilder(Material.PAPER)
                .setGlowing(true)
                .setDisplayName("§7§Settings")
                .addLore("")
                .addLore("§c§lSettings of spectator mode")
                .setPlayerAction(player -> player.openGUI(spectatorSettings));
        spectatorPlayerListItem = new ItemBuilder(Material.COMPASS)
                .setGlowing(true)
                .setDisplayName("§7§lSpectate")
                .addLore("")
                .addLore("§c§lList of all players")
                .setPlayerAction(player -> player.openGUI(spectatorPlayerList));
        //TESTING PART
        // END OF TESTING PART
        registerDefaultEvents();
        registerCommands();
        HackerTools.getPlugin().getSchedulerManager().runLater(() -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (playerManager.getPlayer(player) == null) {
                    PlayerJoinEvent event = new PlayerJoinEvent(player, null);
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }, 20 * 5);
        schedulerManager.addScheduler("scoreboardupdator", Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for(HTPlayer htPlayer : playerManager.getPlayers().values()){
                if (!htPlayer.getPlayer().isOnline()) {
                    continue;
                }
                htPlayer.updateScoreboard();
            }
        }, 20, 20));
        loggerManager.log("HackerTools support enabled!");
    }

    @Override
    public void onLoad() {
        plugin = this;
        pdf = this.getDescription();
        loggerManager = new LoggerManager();
        placeholderManager = new PlaceholderManager();
        mechanics = new Mechanics();
        simpleConfigManager = new SimpleConfigManager(plugin);
        htConfigManager = new HTConfigManager();
        Lang.load();
        minigame = htConfigManager.getConfig().getBoolean("minigame");
        debugManager = new DebugManager();
        eventManager = new EventManager();
        commandManager = new CommandManager();
        chatManager = new ChatManager();
        if(minigame) {
            minigameManager = new MinigameManager();
        }
        entityInteractManager = new EntityInteractManager();
        titleManager = new TitleManager();
        itemInteractManager = new ItemInteractManager();
        schedulerManager = new SchedulerManager();
        guiManager = new GUIManager();
        teamManager = new TeamManager();
        playerManager = new PlayerManager();
        serverManager = new ServerManager();
        randomManager = new RandomManager();
        for (GameState.JoinType jt : GameState.JoinType.values()) {
            jt.setupMessage();
        }
        updater = new Updater();
        pluginsToUpdate.add(new UpdaterPlugin("HackerTools.jar"));
    }

    private void registerDefaultEvents() {
        eventManager.registerInventoryClickEvent();
        eventManager.registerEntityInteract();
        eventManager.registerPlayerJoin();
        eventManager.registerPlayerLogin();
        eventManager.registerPlayerInteract();
        eventManager.registerPlayerLeave();
        eventManager.registerItemDrop();
        eventManager.registerTeamListener();
        eventManager.registerEntityDamage();
        eventManager.registerServerListPing();
        eventManager.registerSpectator();
        eventManager.registerLobbyListener();
    }

    private void registerCommands() {
        getCommand("hackertools").setExecutor(new HTCommand());
        getCommand("debug").setExecutor(new DebugCommand());
    }

    @Override
    public void onDisable() {
        entityInteractManager.getEntities().values().forEach(InteractableEntity::despawn);
        htConfigManager.saveConfigs();
        for (UpdaterPlugin plugin : pluginsToUpdate) {
            if (updater.update(plugin)) {
                loggerManager.log("Successfully updated " + plugin.getSource() + " plugin!");
            } else {
                loggerManager.log("Plugin " + plugin.getSource() + " was not updated.");
            }
        }
        loggerManager.log("HackerTools support disabled!");
    }

    public static HackerTools getPlugin() {
        return plugin;
    }

    public PluginDescriptionFile getPdf() {
        return pdf;
    }

    //GETTERS
    public DebugManager getDebugManager() {
        return debugManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public MinigameManager getMinigameManager() {
        return minigameManager;
    }

    public EntityInteractManager getEntityInteractManager() {
        return entityInteractManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public TitleManager getTitleManager() {
        return titleManager;
    }

    public ItemInteractManager getItemInteractManager() {
        return itemInteractManager;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }

    public SchedulerManager getSchedulerManager() {
        return schedulerManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public SimpleConfigManager getSimpleConfigManager() {
        return simpleConfigManager;
    }

    public HTConfigManager getHtConfigManager() {
        return htConfigManager;
    }

    public LoggerManager getLoggerManager() {
        return loggerManager;
    }

    public GUI getSpectatorSettings() {
        return spectatorSettings;
    }

    public GUI getSpectatorPlayerList() {
        return spectatorPlayerList;
    }

    public Mechanics getMechanics() {
        return mechanics;
    }

    public SpectatorTeam getSpectatorTeam() {
        return spectatorTeam;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    //Interactable items
    public ItemBuilder getForcestartItem() {
        return forcestartItem;
    }

    public ItemBuilder getSpectatorPlayerListItem() {
        return spectatorPlayerListItem;
    }

    public ItemBuilder getSpectatorSettingsItem() {
        return spectatorSettingsItem;
    }

    public GameState getGameState(){
        if(minigameManager == null){
            return GameState.NONE;
        }
        return minigameManager.getGameState();
    }

    public RandomManager getRandomManager() {
        return randomManager;
    }

    public boolean isMinigame() {
        return minigame;
    }
}