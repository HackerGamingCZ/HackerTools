package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.gui.GUIManager;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorPlayerlist;
import cz.HackerGamingCZ.HackerTools.gui.htguis.SpectatorSettings;
import cz.HackerGamingCZ.HackerTools.actions.OpenGUI;
import cz.HackerGamingCZ.HackerTools.actions.PerformCommand;
import cz.HackerGamingCZ.HackerTools.commands.HTCommand;
import cz.HackerGamingCZ.HackerTools.entities.EntityInteractAPI;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import cz.HackerGamingCZ.HackerTools.items.InteractableItem;
import cz.HackerGamingCZ.HackerTools.items.ItemInteractManager;
import cz.HackerGamingCZ.HackerTools.managers.*;
import cz.HackerGamingCZ.HackerTools.managers.SchedulerManager;
import cz.HackerGamingCZ.HackerTools.placeholders.PlaceholderManager;
import cz.HackerGamingCZ.HackerTools.players.PlayerManager;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import cz.HackerGamingCZ.HackerTools.teams.TeamManager;
import cz.HackerGamingCZ.HackerTools.teams.htteams.SpectatorTeam;
import cz.HackerGamingCZ.HackerTools.testing.GreenTeam;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HackerTools extends JavaPlugin {

    private static HackerTools plugin;
    private ItemManager itemManager;
    private DebugManager debugManager;
    private EventManager eventManager;
    private CommandManager commandManager;
    private PlaceholderManager placeholderAPI;
    private ChatManager chatManager;
    private MinigameManager minigameManager;
    private EntityInteractAPI entityInteractAPI;
    private ItemInteractManager itemInteractManager;
    private SchedulerManager schedulerManager;
    private GUIManager guiManager;
    private TitleManager titleManager;
    private TeamManager teamManager;
    private PlayerManager playerManager;
    private Mechanics mechanics;
    private PluginDescriptionFile pdf;

    private InteractableItem forcestartItem;
    private InteractableItem spectatorSettingsItem;
    private InteractableItem spectatorPlayerListItem;

    private GUI spectatorSettings;
    private GUI spectatorPlayerList;

    private Team spectatorTeam;

    private Team greenTeam;

    @Override
    public void onEnable(){
        spectatorSettings = new SpectatorSettings();
        spectatorSettings.register();
        spectatorPlayerList = new SpectatorPlayerlist();
        spectatorPlayerList.register();
        spectatorTeam = new SpectatorTeam();
        forcestartItem  = new InteractableItem(Material.PAPER, 1, "§c§lForcestart", true, (byte)0, new ItemInInventoryClickEvent(new PerformCommand("hackertools forcestart", true)),false, "", "§cClick to forcestart the start");
        forcestartItem.register();
        spectatorSettingsItem = new InteractableItem(Material.PAPER, 1, "§7§lSettings", true, (byte)0, new ItemInInventoryClickEvent(new OpenGUI(spectatorSettings)), false, "", "§cSettings of spectator mode");
        spectatorSettingsItem.register();
        spectatorPlayerListItem = new InteractableItem(Material.COMPASS, 1, "§7§lSpectate", true, (byte)0, new ItemInInventoryClickEvent(new OpenGUI(spectatorPlayerList)), false, "", "§cList of all players");
        spectatorPlayerListItem.register();
        greenTeam = new GreenTeam();
        minigameManager.setupGame(2,3,GameState.WAITING, null, GameMode.CREATIVE);
        minigameManager.enableReconnect();
        registerDefaultEvents();
        registerCommands();
        for(Player player : Bukkit.getOnlinePlayers()){
            playerManager.addPlayer(player);
        }
        Bukkit.getLogger().info("HackerTools support enabled!");
    }

    @Override
    public void onLoad(){
        plugin = this;
        pdf = this.getDescription();
        itemManager = new ItemManager();
        debugManager = new DebugManager();
        eventManager = new EventManager();
        commandManager = new CommandManager();
        placeholderAPI = new PlaceholderManager();
        chatManager = new ChatManager();
        minigameManager = new MinigameManager();
        entityInteractAPI = new EntityInteractAPI();
        titleManager = new TitleManager();
        itemInteractManager = new ItemInteractManager();
        schedulerManager = new SchedulerManager();
        guiManager = new GUIManager();
        teamManager = new TeamManager();
        playerManager = new PlayerManager();
        mechanics = new Mechanics();
    }

    private void registerDefaultEvents(){
        eventManager.registerInventoryClickEvent();
        eventManager.registerEntityInteract();
        eventManager.registerPlayerJoin();
        eventManager.registerPlayerLogin();
        eventManager.registerPlayerInteract();
        eventManager.registerPlayerLeave();
        eventManager.registerItemDrop();
        eventManager.registerTeamListener();
        eventManager.registerEntityDamage();
    }

    private void registerCommands(){
        getCommand("hackertools").setExecutor(new HTCommand());
    }

    @Override
    public void onDisable(){
        entityInteractAPI.getEntities().values().forEach((entity) -> entity.getEntities().forEach(Entity::remove));
        Bukkit.getLogger().info("HackerTools support disabled!");
    }

    public static HackerTools getPlugin() {
        return plugin;
    }
    public PluginDescriptionFile getPdf() { return pdf; }

    public boolean hasSpecialPermission(Player player) {
        return player.hasPermission("ht.*") || player.isOp();
    }
    public boolean hasSpecialPermission(CommandSender sender) {
        return sender.hasPermission("ht.*") || sender.isOp();
    }

    //GETTERS
    public ItemManager getItemManager() { return itemManager; }
    public DebugManager getDebugManager() { return debugManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public PlaceholderManager getPlaceholderAPI() { return placeholderAPI; }
    public ChatManager getChatManager() { return chatManager; }
    public MinigameManager getMinigameManager() { return minigameManager; }
    public EntityInteractAPI getEntityInteractAPI() { return entityInteractAPI; }
    public EventManager getEventManager() { return eventManager; }
    public TitleManager getTitleManager() { return titleManager; }
    public ItemInteractManager getItemInteractManager() { return itemInteractManager; }
    public GUIManager getGuiManager() {
        return guiManager;
    }
    public SchedulerManager getSchedulerManager() {
        return schedulerManager;
    }
    public TeamManager getTeamManager() { return teamManager; }
    public PlayerManager getPlayerManager() { return playerManager; }

    public GUI getSpectatorSettings() { return spectatorSettings; }
    public GUI getSpectatorPlayerList() { return spectatorPlayerList; }

    public Mechanics getMechanics() { return mechanics; }
    public Team getSpectatorTeam() { return spectatorTeam; }

    public Team getGreenTeam() {
        return greenTeam;
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
}