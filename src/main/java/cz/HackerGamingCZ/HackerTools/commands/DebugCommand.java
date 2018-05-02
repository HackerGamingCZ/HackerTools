package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.managers.DebugManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player && HackerTools.getPlugin().hasSpecialPermission(sender) || sender instanceof ConsoleCommandSender){
            if(args.length != 0){
                DebugManager debug = HackerTools.getPlugin().getDebugManager();
                if(!debug.contains(args[0])){
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.DEBUG_DOESNT_EXIST);
                   return true;
                }
                String debugOutput = debug.getDebug(args[0]);
                sender.sendMessage(Lang.DEBUG_PREFIX+debugOutput);
            }
        }
        return true;
    }

}
