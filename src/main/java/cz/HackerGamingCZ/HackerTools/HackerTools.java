package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.actions.PerformCommand;
import cz.HackerGamingCZ.HackerTools.api.*;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import cz.HackerGamingCZ.HackerTools.managers.*;
import org.bukkit.Bukkit;
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
        pdf = this.getDescription();
        registerDefaultEvents();
        Bukkit.getLogger().info("HackerTools support enabled!");
    }

    private void registerDefaultEvents(){
        eventAPI.registerInventoryClickEvent();
        eventAPI.denyJoinMessage();
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

    //MANAGERS
    public ItemManager getItemManager() { return itemManager; }
    public DebugManager getDebugManager() { return debugManager; }
    public EventAPI getEventApi() {
        return eventAPI;
    }
    public CommandManager getCommandManager() { return commandManager; }
    public PlaceholderAPI getPlaceholderApi() { return placeholderAPI; }
    public ChatManager getChatManager() { return chatManager; }
    public MinigameAPI getMinigameAPI() { return minigameAPI; }
    public GUIAPI getGUIAPI() { return GUIAPI; }
}