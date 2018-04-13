package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.Main;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatManager {

    public void sendPlayerMessage(Player player, String message, boolean placeholder){
        if(placeholder){
            message = Main.getPlugin().getPlaceholderManager().replaceString(message);
            message = Main.getPlugin().getPlaceholderManager().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
            player.sendMessage(message);
        } else{
            player.sendMessage(message);
        }
    }
    public void sendPlayerMessage(Player player, String message){
        message = Main.getPlugin().getPlaceholderManager().replaceString(message);
        message = Main.getPlugin().getPlaceholderManager().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
        player.sendMessage(message);
    }

    public void sendPlayerMessage(CommandSender sender, String message){
        message = Main.getPlugin().getPlaceholderManager().replaceString(message);
        message = Main.getPlugin().getPlaceholderManager().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, sender.getName());
        sender.sendMessage(message);
    }

    public void sendPlayerMessage(CommandSender sender, String message, boolean placeholder){
        if(placeholder){
            message = Main.getPlugin().getPlaceholderManager().replaceString(message);
            message = Main.getPlugin().getPlaceholderManager().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, sender.getName());
            sender.sendMessage(message);
        } else{
            sender.sendMessage(message);
        }
    }
}
