package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HTCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0){
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Placeholder.HTPREFIX+"Plugin was made for developers by §c"+Placeholder.PLUGINAUTHOR+"§7.");
            return true;
        }
        if(args[0].equalsIgnoreCase("forcestart") && sender.hasPermission(Permissions.HT_FORCESTART)){
            HackerTools.getPlugin().getMinigameAPI().force();
        }
        return true;
    }
}
