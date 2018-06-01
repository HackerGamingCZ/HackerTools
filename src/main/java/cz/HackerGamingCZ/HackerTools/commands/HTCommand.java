package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.CommandArgument;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
        arguments.put("restart", (player, args) -> HackerTools.getPlugin().getServerManager().restart());
        arguments.put("speed", (player, args) -> {
            if (args.length == 1) {
                return;
            }
            int speed = 1;
            try {
                speed = Integer.parseInt(String.valueOf(args[1]));
            } catch (NumberFormatException ex) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player.getPlayer(), Lang.ARGUMENT_WASNT_NUMBER);
            }
            if (speed > 10) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player.getPlayer(), Lang.TOO_BIG_NUMBER);
                return;
            }
            player.getPlayer().setFlySpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed) / 2);
            player.getPlayer().setWalkSpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed));
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.SUCCESSFULY_SET_SPEED);
        });
        arguments.put("help", (player, args) -> sendHelp(player));
        arguments.put("?", (player, args) -> sendHelp(player));
        arguments.put("list", (player, args) -> sendHelp(player));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            HackerTools.getPlugin().getChatManager().sendBorderedMessage(sender, "■", false, "§c§l" + Placeholders.PLUGINNAME + "§7 Plugin was made for spigot minigame developers by §c§l" + Placeholders.PLUGINAUTHOR);
            return true;
        }
        if (!(sender instanceof Player)) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.SENDER_MUST_BE_PLAYER);
            return true;
        }
        Player player = (Player) sender;
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.UNKNOWN_ERROR);
            return true;
        }
        String command = args[0];
        CommandArgument runnable = arguments.get(command.toLowerCase());
        if (runnable == null) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.HT_COMMAND_ARGUMENT_DOESNT_EXIST);
            player.performCommand("ht ?");
            return true;
        }
        if (Permissions.hasPermission(htPlayer, "ht.command." + command.toLowerCase(), true)) {
            runnable.execute(htPlayer, args);
        }
        return true;
    }

    private void sendHelp(HTPlayer player) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("/ht ? | help - §csends all arguments of §4/ht §ccommand");
        lines.add("/ht restart - §crestarts the server");
        lines.add("/ht forcestart - §cforces the game to start");
        lines.add("/ht speed (number) - §csets your walk and fly speed");
        HackerTools.getPlugin().getChatManager().sendBorderedMessage(player, "■", true, lines);
    }
}
