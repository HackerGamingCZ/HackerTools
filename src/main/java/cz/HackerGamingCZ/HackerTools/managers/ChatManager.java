package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.DefaultFontInfo;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ChatManager {

    private final static int CENTER_PX = 154;
    private String distributor;

    public ChatManager() {
        distributor = HackerTools.getPlugin().getHtConfigManager().getConfig().getString("distributor");
    }

    public void sendCenteredMessage(Player player, String message, boolean placeholder) {
        if (message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else {
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
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        if (placeholder) {
            message = HackerTools.getPlugin().getPlaceholderAPI().replaceString(message, player);
            player.sendMessage(message);
        } else {
            player.sendMessage(message);
        }
    }

    public void hoverableText(Player player, String message, String tooltip) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        player.spigot().sendMessage(component);
    }

    public void sendTooltippedTextPerformingCommand(Player player, String message, String tooltip, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        command = "/" + command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTextPerformingCommand(Player player, String message, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        command = "/" + command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTooltippedTextSuggestingCommand(Player player, String message, String tooltip, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        command = "/" + command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public void sendTextSuggestingCommand(Player player, String message, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        command = "/" + command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        player.spigot().sendMessage(component);
    }

    public static void sendCenteredMessage(Player player, String message) {
        if (message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode == true) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else {
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
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public void sendPlayerMessage(Player player, String message, boolean placeholder) {
        if (placeholder) {
            message = HackerTools.getPlugin().getPlaceholderAPI().replaceString(message, player);
            player.sendMessage(message);
        } else {
            player.sendMessage(message);
        }
    }

    public void sendPlayerMessage(Collection<? extends Player> players, String message, boolean placeholder) {
        for (Player player : players) {
            sendPlayerMessage(player, message, placeholder);
        }
    }

    public void sendPlayerMessage(Collection<? extends Player> players, String message, String specialPlayer) {
        for (Player player : players) {
            sendPlayerMessage(player, message, specialPlayer);
        }
    }

    public void sendPlayerMessage(Player player, String message, String specialPlayer) {
        message = HackerTools.getPlugin().getPlaceholderAPI().replaceString(message, Bukkit.getOfflinePlayer(specialPlayer));
        player.sendMessage(message);
    }


    public void sendPlayerMessage(Player player, String message) {
        sendPlayerMessage(player, message, true);
    }

    public void sendPlayerMessage(CommandSender sender, String message) {
        sendPlayerMessage(sender, message, true);
    }

    public void sendPlayerMessage(CommandSender sender, String message, boolean placeholder) {
        if (placeholder) {
            message = HackerTools.getPlugin().getPlaceholderAPI().replaceString(message);
            sender.sendMessage(message);
        } else {
            sender.sendMessage(message);
        }
    }

    public String getDistributor() {
        return distributor;
    }
}
