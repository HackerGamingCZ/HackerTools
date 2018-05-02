package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.DefaultFontInfo;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatManager {

    private final static int CENTER_PX = 154;

    public void sendCenteredMessage(Player player, String message, boolean placeholder){
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        if(placeholder) {
            message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
            message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
            player.sendMessage(message);
        }else{
            player.sendMessage(message);
        }
    }

    public void hoverableText(Player player, String message, String tooltip){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        player.spigot().sendMessage(component);
    }

    public void sendTooltippedTextPerformingCommand(Player player, String message, String tooltip, String command){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        command = "/"+command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTextPerformingCommand(Player player, String message, String command){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        command = "/"+command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTooltippedTextSuggestingCommand(Player player, String message, String tooltip, String command){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        command = "/"+command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTextSuggestingCommand(Player player, String message, String command){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        command = "/"+command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendCenteredMessage(Player player, String message){
        if(message == null || message.equals("")) {
            player.sendMessage("");
            return;
        }
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
        message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
        player.sendMessage(message);
    }

    public void sendPlayerMessage(Player player, String message, boolean placeholder){
        if(placeholder){
            message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
            message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
            player.sendMessage(message);
        } else{
            player.sendMessage(message);
        }
    }
    public void sendPlayerMessage(Player player, String message){
        message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
        message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, player.getName());
        player.sendMessage(message);
    }

    public void sendPlayerMessage(CommandSender sender, String message){
        message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
        message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, sender.getName());
        sender.sendMessage(message);
    }

    public void sendPlayerMessage(CommandSender sender, String message, boolean placeholder){
        if(placeholder){
            message = HackerTools.getPlugin().getPlaceholderApi().replaceString(message);
            message = HackerTools.getPlugin().getPlaceholderApi().replaceSpecialPlaceholder(message, Placeholder.PLAYERNAME, sender.getName());
            sender.sendMessage(message);
        } else{
            sender.sendMessage(message);
        }
    }

}
