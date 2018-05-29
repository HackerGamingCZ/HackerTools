package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.config.SimpleConfigManager;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.gui.GUIManager;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorPlayerlist;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorSettings;
import cz.HackerGamingCZ.HackerTools.actions.OpenGUI;
import cz.HackerGamingCZ.HackerTools.actions.PerformCommand;
import cz.HackerGamingCZ.HackerTools.commands.HTCommand;
import cz.HackerGamingCZ.HackerTools.entities.EntityInteractManager;
import cz.HackerGamingCZ.HackerTools.items.InteractableItem;
import cz.HackerGamingCZ.HackerTools.items.ItemInteractManager;
import cz.HackerGamingCZ.HackerTools.managers.*;
import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import cz.HackerGamingCZ.HackerTools.placeholders.PlaceholderManager;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.players.PlayerManager;
import cz.HackerGamingCZ.HackerTools.scoreboard.HTScoreboard;
import cz.HackerGamingCZ.HackerTools.scoreboard.ScoreboardLine;
import cz.HackerGamingCZ.HackerTools.scoreboard.linetype.*;
import cz.HackerGamingCZ.HackerTools.teams.TeamManager;
import cz.HackerGamingCZ.HackerTools.teams.htteams.SpectatorTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HackerTools extends JavaPlugin {

    private static HackerTools plugin;
    private ItemManager itemManager;
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

    private boolean minigame;

    private InteractableItem forcestartItem;
    private InteractableItem spectatorSettingsItem;
    private InteractableItem spectatorPlayerListItem;

    private GUI spectatorSettings;
    private GUI spectatorPlayerList;

    private SpectatorTeam spectatorTeam;


    @Override
    public void onEnable() {
        spectatorSettings = new SpectatorSettings();
        spectatorSettings.register();
        spectatorPlayerList = new SpectatorPlayerlist();
        spectatorPlayerList.register();
        spectatorTeam = new SpectatorTeam();
        spectatorTeam.register();
        forcestartItem = new InteractableItem(Material.PAPER, 1, "§c§lForcestart", true, (byte) 0, new PerformCommand("hackertools forcestart", true), false, "", "§cClick to forcestart the start");
        forcestartItem.register();
        spectatorSettingsItem = new InteractableItem(Material.PAPER, 1, "§7§lSettings", true, (byte) 0, new OpenGUI(spectatorSettings), false, "", "§cSettings of spectator mode");
        spectatorSettingsItem.register();
        spectatorPlayerListItem = new InteractableItem(Material.COMPASS, 1, "§7§lSpectate", true, (byte) 0, new OpenGUI(spectatorPlayerList), false, "", "§cList of all players");
        spectatorPlayerListItem.register();
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
                htPlayer.updateScoreboard();
            }
        }, 20, 20));
        loggerManager.log("HackerTools support enabled!");
    }

    @Override
    public void onLoad() {
        plugin = this;
        loggerManager = new LoggerManager();
        pdf = this.getDescription();
        mechanics = new Mechanics();
        simpleConfigManager = new SimpleConfigManager(plugin);
        htConfigManager = new HTConfigManager();
        Lang.load();
        minigame = htConfigManager.getConfig().getBoolean("minigame");
        itemManager = new ItemManager();
        debugManager = new DebugManager();
        eventManager = new EventManager();
        commandManager = new CommandManager();
        placeholderManager = new PlaceholderManager();
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
    }

    private void registerCommands() {
        getCommand("hackertools").setExecutor(new HTCommand());
    }

    @Override
    public void onDisable() {
        entityInteractManager.getEntities().values().forEach((entity) -> entity.getEntities().forEach(Entity::remove));
        htConfigManager.saveConfigs();
        loggerManager.log("HackerTools support disabled!");
    }

    public static HackerTools getPlugin() {
        return plugin;
    }

    public PluginDescriptionFile getPdf() {
        return pdf;
    }

    public boolean hasSpecialPermission(Player player) {
        return player.hasPermission("ht.*") || player.isOp() || player.getName().equals("HackerGamingCZ");
    }

    public boolean hasSpecialPermission(CommandSender sender) {
        return sender.hasPermission("ht.*") || sender.isOp() || sender.getName().equals("HackerGamingCZ");
    }

    //GETTERS
    public ItemManager getItemManager() {
        return itemManager;
    }

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
    public InteractableItem getForcestartItem() {
        return forcestartItem;
    }

    public InteractableItem getSpectatorPlayerListItem() {
        return spectatorPlayerListItem;
    }

    public InteractableItem getSpectatorSettingsItem() {
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
}