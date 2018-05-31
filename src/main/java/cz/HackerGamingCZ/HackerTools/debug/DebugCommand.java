package cz.HackerGamingCZ.HackerTools.debug;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.debug.DebugManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Permissions.hasPermission(sender, Permissions.DEBUG_COMMAND)) {
            HackerTools.getPlugin().getChatManager().notEnoughPermissionsError(sender);
            return true;
        }
        if (args.length == 0) {
            HackerTools.getPlugin().getChatManager().notEnoughArgumentsError(sender, "Type §c/debug help §7for help.");
            return true;
        }
        DebugManager debug = HackerTools.getPlugin().getDebugManager();
        if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("list")) {
            sendHelp(sender);
            return true;
        }
        Debug debugOutput = debug.getDebug(args[0]);
        if (debugOutput == null) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.DEBUG_DOESNT_EXIST);
            return true;
        }
        sender.sendMessage(Lang.DEBUG_PREFIX + debugOutput.getOutput());
        return true;
    }

    private void sendHelp(CommandSender sender) {
        ArrayList<String> lines = new ArrayList<>();
        for (String string : HackerTools.getPlugin().getDebugManager().getAllDebugs().keySet()) {
            Debug debug = HackerTools.getPlugin().getDebugManager().getDebug(string);
            lines.add("§4" + string + "§7 -  §c" + debug.getDescription());
        }
        HackerTools.getPlugin().getChatManager().sendBorderedMessage(sender, "■", true, lines);
    }

}
