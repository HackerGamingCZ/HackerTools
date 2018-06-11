package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.CommandArgument;
import cz.HackerGamingCZ.HackerTools.entities.InteractableEntity;
import cz.HackerGamingCZ.HackerTools.managers.ChatManager;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
            if (reason.length() > 0) {
                victim.kickPlayer("§6You have been kicked for: §e" + reason);
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with reason: " + reason + ".");
            } else {
                victim.kickPlayer("§6You have been kicked!");
                HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with no reason.");
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Permissions.hasPermission(p, Permissions.KICK_ANNOUNCEMENT_SHOW, false)) {
                    if (reason.length() > 0) {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked player §e" + victim.getName() + "§7 with reason: §e" + reason + "§7.");
                    } else {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked player §e" + victim.getName() + "§7 with no reason.");
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
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked player §eall players §7with reason: §e" + reason + "§7.");
                    } else {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked §eall players §7with no reason.");
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
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht - §csends informations about §4" + Placeholders.PLUGINNAME + " §cplugin", "§7Click to perform §c/ht", "ht"));
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht ? | help - §csends all arguments of §4/ht §ccommand", "§7Click to perform §c/ht help", "ht help"));
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
        lines.add(manager.getTooltippedTextPerformingCommand(player, "§7/ht entities - §cshows list of all entities spawned using §4" + Placeholders.PLUGINNAME, "ht entities", "§7Click to perform §c/ht entities"));
        lines.add(manager.getTooltippedTextSuggestingCommand(player, "§7/ht entities remove <id> - §c", "ht entities remove ", "§7Click to suggest §2/ht entities remove"));
        HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", lines);
    }
}
