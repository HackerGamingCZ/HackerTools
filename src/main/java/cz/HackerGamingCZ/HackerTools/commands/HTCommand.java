package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.CommandArgument;
import cz.HackerGamingCZ.HackerTools.managers.ChatManager;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

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
                    HackerTools.getPlugin().getChatManager().broadcastSpecialMessage(ChatManager.SpecialMessageType.ALERT, "SYSTEM", " §2»§a This server is going to be restarted.", " §2»§a You'll be kicked in §2" + i[0] + "§a seconds.");
                } else {
                    HackerTools.getPlugin().getChatManager().broadcastSpecialMessage(ChatManager.SpecialMessageType.ALERT, "SYSTEM", " §2»§a Restarting...", "");
                    HackerTools.getPlugin().getServerManager().kickAll(false, "restart");
                    HackerTools.getPlugin().getServerManager().restart();
                }
                i[0]--;
            }, 0, 20);
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
            } else {
                victim.kickPlayer("§6You have been kicked!");
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Permissions.hasPermission(p, "hackertools.kick.announce", false)) {
                    if (reason.length() > 0) {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked player §e" + victim.getName() + "§7 with reason: §e" + reason + "§7.");
                        HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with no reason.");
                    } else {
                        HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, Placeholders.HTPREFIX + "Administrator §e" + player.getName() + " §7kicked player §e" + victim.getName() + "§7 with no reason.");
                        HackerTools.getPlugin().getLoggerManager().log("Administrator " + player.getName() + " kicked player " + victim.getName() + " with no reason.");
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
                line1 = " §2» §a" + HackerTools.getPlugin().getMechanics().color(HackerTools.getPlugin().getMechanics().argConvert(size, 0, lines));
                line2 = " §2» §a" + HackerTools.getPlugin().getMechanics().color(HackerTools.getPlugin().getMechanics().argConvert(size, lines, size.length));
            } else {
                line1 = " §2» §a" + HackerTools.getPlugin().getMechanics().color(message);
            }
            HackerTools.getPlugin().getChatManager().broadcastSpecialMessage(ChatManager.SpecialMessageType.ALERT, player, line1, line2);
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
        });
        arguments.put("?", (player, args) -> sendHelp(player));
        arguments.put("list", (player, args) -> sendHelp(player));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            HackerTools.getPlugin().getChatManager().sendBorderedMessage(sender, "■", false, "§c§l" + Placeholders.PLUGINNAME + "§7 Plugin was made for spigot minigame developers by §c§l" + Placeholders.PLUGINAUTHOR, "The version of " + Placeholders.PLUGINNAME + " is §c§l" + Placeholders.PLUGINVERSION);
            return true;
        }
        String command = args[0];
        CommandArgument runnable = arguments.get(command.toLowerCase());
        if (runnable == null) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.HT_COMMAND_ARGUMENT_DOESNT_EXIST);
            return true;
        }
        if (Permissions.hasPermission(sender, "ht.command." + command.toLowerCase(), true)) {
            runnable.execute(sender, args);
        }
        return true;
    }

    private void sendHelp(CommandSender player) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("/ht - §csends informations about §4" + Placeholders.PLUGINNAME + " §cplugin");
        lines.add("/ht ? | help - §csends all arguments of §4/ht §ccommand");
        lines.add("/ht restart [number] - §crestarts the server");
        lines.add("/ht forcestart - §cforces the game to start");
        lines.add("/ht speed <number> - §csets your walk and fly speed");
        lines.add("/ht kick <player> [reason] - §ckicks a player");
        lines.add("/ht alert <message> - §csends alert message");
        HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", true, lines);
    }
}
