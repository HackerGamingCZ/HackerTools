package cz.HackerGamingCZ.HackerTools.commands;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Permissions;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HTCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0){
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Placeholder.HTPREFIX+"Plugin was made for spigot minigame developers by §c"+Placeholder.PLUGINAUTHOR+"§7.");
            return true;
        }
        if(sender instanceof Player) {
            HTPlayer player = HackerTools.getPlugin().getPlayerManager().getPlayer((Player) sender);
            if (args[0].equalsIgnoreCase("speed") && (player.isSpectator() || sender.hasPermission(Permissions.HT_SPEED))) {
                if (args.length == 1) {
                    return true;
                }
                int speed = 1;
                try {
                    speed = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(sender, Lang.ARGUMENT_WASNT_NUMBER);
                }
                ((Player) sender).setFlySpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed) / 2);
                ((Player) sender).setWalkSpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(speed));
            }
        }
        if(args[0].equalsIgnoreCase("forcestart") && sender.hasPermission(Permissions.HT_FORCESTART)){
            HackerTools.getPlugin().getMinigameManager().force();
        }
        return true;
    }

}
