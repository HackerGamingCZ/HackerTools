package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.CommandArgument;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.entities.InteractableEntity;
import cz.HackerGamingCZ.HackerTools.managers.ChatManager;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HTCommand implements CommandExecutor {

    private HashMap<String, CommandArgument> arguments = new HashMap<>();

    public HTCommand() {
        arguments.put("forcestart", (player, args) -> {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.FORCESTART_EXECUTED);
            HackerTools.getPlugin().getMinigameManager().force();
        });
        arguments.put("restart", (player, args) -> {
            final int[] i = {10};
            if (args.length > 1) {
                try {
                    i[0] = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                }
            }
            Bukkit.getScheduler().scheduleSyncRepeatingTask(HackerTools.getPlugin(), () -> {
                if (i[0] > 0) {
                    HackerTools.getPlugin().getChatManager().sendSpecialMessage(Bukkit.getOnlinePlayers(), ChatManager.SpecialMessageType.ALERT, "SYSTEM", "This server is going to be restarted.", "You'll be kicked in §2" + i[0] + "§a seconds.");
                } else {
                    HackerTools.getPlugin().getChatManager().sendSpecialMessage(Bukkit.getOnlinePlayers(), ChatManager.SpecialMessageType.ALERT, "SYSTEM", "Restarting...", "");
                    HackerTools.getPlugin().getServerManager().kickAll(false, "restart");
                    HackerTools.getPlugin().getServerManager().restart();
                }
                i[0]--;
            }, 0, 20);
        });
        arguments.put("serverinfo", (player, args) -> {
            Server server = Bukkit.getServer();
            ArrayList<String> text = new ArrayList<>();
            text.add("Name: §c" + server.getName());
            text.add("Server name: §c" + server.getServerName());
            text.add("Server ID: §c" + server.getServerId());
            if (Permissions.hasPermission(player, Permissions.SERVER_INFO_SHOW_SERVER_IP)) {
                text.add("IP: §c" + server.getIp());
                text.add("port: §c" + server.getPort());
            }
            text.add("MOTD: §c" + server.getMotd());
            text.add("Server version: §c" + server.getVersion());
            text.add("Bukkit version: §c" + server.getBukkitVersion());
            text.add("Online mode: §c" + server.getOnlineMode());
            text.add("Whitelist: §c" + server.hasWhitelist());
            text.add("Max players: §c" + server.getMaxPlayers());
            text.add("Online players: §c" + server.getOnlinePlayers().size());
            text.add("Idle timeout: §c" + server.getIdleTimeout());
            text.add("Connection throttle: §c" + server.getConnectionThrottle());
            text.add("Hardcore mode: §c" + server.isHardcore());
            text.add("Allow end: §c" + server.getAllowEnd());
            text.add("Allow nether: §c" + server.getAllowNether());
            text.add("Allow flight: §c" + server.getAllowFlight());
            text.add("Default gamemode: §c" + server.getDefaultGameMode());

            text.add("Ambient spawn limit: §c" + server.getAmbientSpawnLimit());
            text.add("Animal spawn limit: §c" + server.getAnimalSpawnLimit());
            text.add("Water animal spawn limit: §c" + server.getWaterAnimalSpawnLimit());
            text.add("Monster spawn limit: §c" + server.getMonsterSpawnLimit());
            text.add("Ticks per animal spawns: §c" + server.getTicksPerAnimalSpawns());
            text.add("Ticks per monster spawns: §c" + server.getTicksPerMonsterSpawns());
            text.add("Spawn radius: §c" + server.getSpawnRadius());
            text.add("Generate structures: §c" + server.getGenerateStructures());
            text.add("View distance: §c" + server.getViewDistance());
            text.add("World type: §c" + server.getWorldType());

            text.add("Shutdown message: §c" + server.getShutdownMessage());
            text.add("Primary thread: §c" + server.isPrimaryThread());
            text.add("Update folder: §c" + server.getUpdateFolder());
            HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", true, text);
        });
        arguments.put("colors", (player, args) -> {
            ArrayList<String> text = new ArrayList<>();
            for (ChatColor color : ChatColor.values()) {
                if (color.isFormat()) {
                    continue;
                }
                text.add(color + "█&" + color.getChar() + "§7 - " + color + "example text of ChatColor." + color.name());
            }

            HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", true, text);
        });
        arguments.put("formats", (player, args) -> {
            ArrayList<String> text = new ArrayList<>();
            for (ChatColor format : ChatColor.values()) {
                if (format.isColor()) {
                    continue;
                }
                text.add(format + "&" + format.getChar() + " §7- " + format + " example text of ChatColor." + format.name());
            }

            HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", true, text);
        });
        arguments.put("entities", (player, args) -> {
            if (args.length <= 1 || args[1].equalsIgnoreCase("list")) {
                ArrayList<TextComponent> components = new ArrayList<>();
                for (InteractableEntity interactableEntity : HackerTools.getPlugin().getEntityInteractManager().getEntities().values()) {
                    for (Entity entity : interactableEntity.getEntities()) {
                        Location location = entity.getLocation();
                        components.add(HackerTools.getPlugin().getChatManager().getTooltippedTextPerformingCommand(player, "§aEntity with id §2" + interactableEntity.getId() + "§a. Click here to teleport.", "teleport " + player.getName() + " " + Math.round(location.getX()) + " " + Math.round(location.getY()) + " " + Math.round(location.getZ()), "§aClick to teleport to entity's location"));
                    }
                }
                if (components.size() == 0) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.NO_ENTITIES_FOUND);
                }
                HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", components);
            }
        });
        arguments.put("speed", (player, args) -> {
            if (!(player instanceof Player)) {
                return;
            }
            Player realPlayer = (Player) player;
            if (args.length < 2) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.NOT_ENOUGH_ARGUMENTS);
                return;
            }
            int speed = 1;
            try {
                speed = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.ARGUMENT_WASNT_NUMBER);
            }
            if (speed > 10) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.TOO_BIG_NUMBER);
                return;
            }
            realPlayer.setFlySpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed) / 2);
            realPlayer.setWalkSpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed));
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.SUCCESSFULY_SET_SPEED);
        });

        arguments.put("debug", (player, args) -> {
            if (!(player instanceof Player)) {
                return;
            }
            HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer((Player) player);
            if (args.length < 2) {
                if (htPlayer.isDebug()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Placeholders.DEBUGPREFIX + "Debug was disabled for you!");
                } else {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Placeholders.DEBUGPREFIX + "Debug was enabled for you!");
                }
                htPlayer.setDebug(!htPlayer.isDebug());
                return;
            }
            String action = args[1];
            if (action.equals("disable") || action.equals("off")) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Placeholders.DEBUGPREFIX + "Debug was disabled for you!");
                htPlayer.setDebug(false);
            } else if (action.equals("enable") || action.equals("on")) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Placeholders.DEBUGPREFIX + "Debug was enabled for you!");
                htPlayer.setDebug(true);
            } else {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.WRONG_ARGUMENT);
            }
        });
        arguments.put("kick", (player, args) -> {
            if (args.length < 2) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.NOT_ENOUGH_ARGUMENTS);
                return;
            }
            Player victim = Bukkit.getPlayer(args[1]);
            if (victim == null) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.PLAYER_NOT_FOUND);
                return;
            }
            String reason = "";
            for (int i = 2; i < args.length; i++) {
                if (i == (args.length - 1)) {
                    reason += args[i] + "";
                } else {
                    reason += args[i] + " ";
                }
            }
            boolean op = false;
            if (reason.contains("-op")) {
                op = true;
                reason = reason.replace("-op", "");
            }
            if (Permissions.hasPermission(victim, Permissions.KICK_PROTECTION)) {
                if (!op) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.PLAYER_HAS_KICK_PROTECTION);
                    return;
                }
            }
            if (reason.length() > 0) {
                victim.kickPlayer("§cYou have been kicked for: §4" + reason);
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with reason: " + reason + ".");
            } else {
                victim.kickPlayer("§cYou have been kicked!");
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with no reason.");
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Permissions.hasPermission(p, Permissions.KICK_ANNOUNCEMENT_SHOW, false)) {
                    if (reason.length() > 0) {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.PUNISHMENTPREFIX + "Administrator §c" + player.getName() + " §7kicked player §c" + victim.getName() + "§7 with reason: §c" + reason + "§7.");
                    } else {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.PUNISHMENTPREFIX + "Administrator §c" + player.getName() + " §7kicked player §c" + victim.getName() + "§7 with no reason.");
                    }
                }
            }
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.SUCCESSFULY_KICKED_PLAYER);
        });
        arguments.put("alert", (player, args) -> {
            String message = "";
            String line1;
            String line2 = "";
            for (int i = 1; i < args.length; i++) {
                message += args[i] + " ";
            }
            if (message.length() >= 60) {
                String[] size = message.split(" ");
                int lines = size.length % 2 == 0 ? size.length / 2 : (size.length + 1) / 2;
                line1 = HackerTools.getPlugin().getMechanics().color(HackerTools.getPlugin().getMechanics().argConvert(size, 0, lines));
                line2 = HackerTools.getPlugin().getMechanics().color(HackerTools.getPlugin().getMechanics().argConvert(size, lines, size.length));
            } else {
                line1 = HackerTools.getPlugin().getMechanics().color(message);
            }
            HackerTools.getPlugin().getChatManager().sendSpecialMessage(Bukkit.getOnlinePlayers(), ChatManager.SpecialMessageType.ALERT, player, line1, line2);
        });
        arguments.put("help", (player, args) -> sendHelp(player));
        arguments.put("kickall", (player, args) -> {
            String reason = "";
            for (int i = 1; i < args.length; i++) {
                if (i == (args.length - 1)) {
                    reason += args[i] + "";
                } else {
                    reason += args[i] + " ";
                }
            }
            HackerTools.getPlugin().getServerManager().kickAll(true, reason);

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Permissions.hasPermission(p, Permissions.KICK_ANNOUNCEMENT_SHOW, false)) {
                    if (reason.length() > 0) {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.PUNISHMENTPREFIX + "Administrator §c" + player.getName() + " §7kicked player §call players §7with reason: §c" + reason + "§7.");
                    } else {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.PUNISHMENTPREFIX + "Administrator §c" + player.getName() + " §7kicked §call players §7with no reason.");
                    }
                }
            }
            if (reason.length() > 0) {
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked all players with reason: " + reason + ".");
            } else {
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked all players with no reason.");
            }
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.SUCCESSFULY_KICKED_ALL_PLAYERS);
        });
        arguments.put("?", (player, args) -> sendHelp(player));
        arguments.put("list", (player, args) -> sendHelp(player));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            ArrayList<TextComponent> components = new ArrayList<>();
            components.add(HackerTools.getPlugin().getChatManager().getTooltippedLinkMessage(sender, "§c§l" + Placeholders.PLUGINNAME + "§7 Plugin was made for spigot minigame developers by §c§l" + Placeholders.PLUGINAUTHOR, "https://github.com/HackerGamingCZ/HackerTools/tree/master/src/main/java/cz/HackerGamingCZ/HackerTools", "§7Open §cGit §7of HackerTools"));
            components.add(HackerTools.getPlugin().getChatManager().getTooltippedLinkMessage(sender, "§7The version of " + Placeholders.PLUGINNAME + " is §c§l" + Placeholders.PLUGINVERSION, "https://github.com/HackerGamingCZ/HackerTools/tree/master/src/main/java/cz/HackerGamingCZ/HackerTools", "§7Open §cGit §7of HackerTools"));
            components.add(HackerTools.getPlugin().getChatManager().getTooltippedTextPerformingCommand(sender, "§7To see all arguments of §c/ht§7, type §c/ht help §7or click §chere§7.", "ht help", "§7Click to suggest §c/ht help"));
            HackerTools.getPlugin().getChatManager().sendBorderedMessage(sender, "■", components);
            return true;
        }
        String command = args[0];
        CommandArgument runnable = arguments.get(command.toLowerCase());
        if (runnable == null) {
            sendHelp(sender);
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.HT_COMMAND_ARGUMENT_DOESNT_EXIST);
            return true;
        }
        if (Permissions.hasPermission(sender, "ht.command." + command.toLowerCase(), true)) {
            runnable.execute(sender, args);
        }
        return true;
    }

    private void sendHelp(CommandSender player) {
        ArrayList<TextComponent> lines = new ArrayList<>();
        ChatManager manager = HackerTools.getPlugin().getChatManager();
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht - §csends informations about §4" + Placeholders.PLUGINNAME + " §cplugin", "ht", "§7Click to perform §c/ht"));
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht ? | help - §csends all arguments of §4/ht §ccommand", "ht help", "§7Click to perform §c/ht help"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht restart [seconds] - §crestarts the server. Default time is §410 seconds", "ht restart ", "§7Click to suggest §c/ht restart"));
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht forcestart - §cforces the game to start", "ht forcestart", "§7Click to perform §c/ht forcestart"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht speed <number> - §csets your walk and fly speed", "ht speed ", "§7Click to suggest §c/ht speed"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht kick <player> [reason] - §ckicks a player", "ht kick ", "§7Click to suggest §c/ht kick"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht kickall [reason] - §ckicks all players except those with §4bypass §cpermission", "ht kickall ", "§7Click to suggest §c/ht kickall"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht alert <message> - §csends alert message", "ht alert ", "§7Click to suggest §c/ht alert ", "§cMessage will look like: ", "",
                "§4████████",
                "§4███§f██§4███",
                "§4███§f██§4███  §2»§a First message line",
                "§4███§f██§4███  §2»§a Second message line if needed",
                "§4███§f██§4███",
                "§4████████ §7§o- " + Placeholders.PLAYERNAME,
                "§4███§f██§4███",
                "§4████████"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht debug <on/off> - §cturn off or on automatic debug sending.", "ht debug ", "§7Click to suggest §c/ht debug"));
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht entities - §cshows list of all entities spawned using §4" + Placeholders.PLUGINNAME, "ht entities", "§7Click to perform §c/ht entities"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht entities remove <id> - §c", "ht entities remove ", "§7Click to suggest §c/ht entities remove"));
        HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", lines);
    }
}
