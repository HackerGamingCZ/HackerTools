package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    private ItemManager itemManager;
    private DebugManager debugManager;
    private MinigameManager minigameManager;
    private EventManager eventManager;
    private CommandManager commandManager;
    private PlaceholderManager placeholderManager;
    private ChatManager chatManager;
    private PluginDescriptionFile pdf;

    @Override
    public void onEnable(){
        plugin = this;
        itemManager = new ItemManager();
        debugManager = new DebugManager();
        minigameManager = new MinigameManager();
        eventManager = new EventManager();
        commandManager = new CommandManager();
        placeholderManager = new PlaceholderManager();
        chatManager = new ChatManager();
        pdf = this.getDescription();
        Bukkit.getLogger().warning("HackerTools support enabled!");
    }

    @Override
    public void onDisable(){
        Bukkit.getLogger().warning("HackerTools support disabled!");
    }

    public static Main getPlugin() {
        return plugin;
    }
    public PluginDescriptionFile getPdf() { return pdf; }

    public boolean hasPermission(Player player) {
        return player.hasPermission("ht.*") || player.isOp();
    }
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission("ht.*") || sender.isOp();
    }

    //MANAGERS
    public ItemManager getItemManager() { return itemManager; }
    public DebugManager getDebugManager() { return debugManager; }
    public EventManager getEventManager() {
        return eventManager;
    }
    public MinigameManager getMinigameManager() { return minigameManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public PlaceholderManager getPlaceholderManager() { return placeholderManager; }
    public ChatManager getChatManager() { return chatManager; }
}