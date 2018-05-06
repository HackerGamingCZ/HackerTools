package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.actions.OpenGUI;
import cz.HackerGamingCZ.HackerTools.actions.PerformCommand;
import cz.HackerGamingCZ.HackerTools.api.*;
import cz.HackerGamingCZ.HackerTools.commands.HTCommand;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HackerTools extends JavaPlugin {

    private static HackerTools plugin;
    private ItemManager itemManager;
    private DebugManager debugManager;
    private EventAPI eventAPI;
    private CommandManager commandManager;
    private PlaceholderAPI placeholderAPI;
    private ChatManager chatManager;
    private MinigameAPI minigameAPI;
    private GUIAPI GUIAPI;
    private EntityInteractAPI entityInteractAPI;
    private ItemInteractAPI itemInteractAPI;
    private SchedulerAPI schedulerAPI;
    private TitleAPI titleAPI;
    private PluginDescriptionFile pdf;

    @Override
    public void onEnable(){
        plugin = this;
        itemManager = new ItemManager();
        debugManager = new DebugManager();
        eventAPI = new EventAPI();
        commandManager = new CommandManager();
        placeholderAPI = new PlaceholderAPI();
        chatManager = new ChatManager();
        minigameAPI = new MinigameAPI();
        GUIAPI = new GUIAPI();
        entityInteractAPI = new EntityInteractAPI();
        titleAPI = new TitleAPI();
        itemInteractAPI = new ItemInteractAPI();
        schedulerAPI = new SchedulerAPI();
        pdf = this.getDescription();
        registerDefaultEvents();
        registerCommands();
        minigameAPI.setGameState(GameState.SETUP);
        Bukkit.getLogger().info("HackerTools support enabled!");
        itemInteractAPI.addItem("forcestart", new InteractableItem(Material.PAPER, 1, "§c§lForcestart", new String[]{}, true, (byte)0, new PerformCommand("hackertools forcestart", true)));
        itemInteractAPI.addItem("spectsettings", new InteractableItem(Material.PAPER, 1, "§7§lSettings", new String[]{"", "§cSettings of spectator mode"}, true, (byte)0, new OpenGUI("spectsettings", true)));
        itemInteractAPI.addItem("spectatelist", new InteractableItem(Material.COMPASS, 1, "§7§lSpectate", new String[]{"", "§cList of all players"}, true, (byte)0, new OpenGUI("spectateplayers", true)));
     }

    private void registerDefaultEvents(){
        eventAPI.registerInventoryClickEvent();
        eventAPI.registerEntityInteract();
        eventAPI.registerPlayerJoin();
        eventAPI.registerPlayerLogin();
        eventAPI.registerPlayerInteract();
        eventAPI.registerPlayerLeave();
    }

    private void registerCommands(){
        getCommand("hackertools").setExecutor(new HTCommand());
    }

    @Override
    public void onDisable(){
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
    public PlaceholderAPI getPlaceholderAPI() { return placeholderAPI; }
    public ChatManager getChatManager() { return chatManager; }
    public MinigameAPI getMinigameAPI() { return minigameAPI; }
    public GUIAPI getGUIAPI() { return GUIAPI; }
    public EntityInteractAPI getEntityInteractAPI() { return entityInteractAPI; }
    public EventAPI getEventAPI() { return eventAPI; }
    public TitleAPI getTitleAPI() { return titleAPI; }
    public ItemInteractAPI getItemInteractAPI() { return itemInteractAPI; }

    public SchedulerAPI getSchedulerAPI() {
        return schedulerAPI;
    }
}