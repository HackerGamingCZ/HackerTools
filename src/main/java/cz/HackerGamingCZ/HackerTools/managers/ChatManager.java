package cz.HackerGamingCZ.HackerTools.managers;

import com.google.common.base.Strings;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.DefaultFontInfo;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class ChatManager {

    private final static int CENTER_PX = 154;
    private String distributor;

    public ChatManager() {
        distributor = HackerTools.getPlugin().getHtConfigManager().getConfig().getString("distributor");
    }

    public void sendCenteredMessage(CommandSender player, String message, boolean placeholder) {
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
            message = HackerTools.getPlugin().getPlaceholderManager().replaceString(message, player);
            player.sendMessage(message);
        } else {
            player.sendMessage(message);
        }
    }

    public void notEnoughArgumentsError(CommandSender sender) {
        notEnoughArgumentsError(sender, "");
    }

    public void notEnoughArgumentsError(CommandSender sender, String additionalText) {
        sendPlayerMessage(sender, Lang.NOT_ENOUGH_ARGUMENTS + " " + additionalText);
    }

    public void notEnoughArgumentsError(HTPlayer player) {
        notEnoughArgumentsError(player.getPlayer());
    }

    public void notEnoughArgumentsError(HTPlayer player, String additionalText) {
        notEnoughArgumentsError(player.getPlayer(), additionalText);
    }


    public void sendBorderedMessage(CommandSender sender, String borderChar, boolean arrows, String... text) {
        if (text.length <= 0) {
            return;
        }
        if (sender instanceof ConsoleCommandSender) {
            borderChar = "-";
        }
        String border = Strings.repeat(borderChar, 64);
        sender.sendMessage("§8" + border);
        sender.sendMessage("");
        for (String string : text) {
            sender.sendMessage((arrows || string.length() <= 0 ? "§8» " : "") + "§7" + HackerTools.getPlugin().getPlaceholderManager().replaceString(string, sender));
        }
        sender.sendMessage("");
        sender.sendMessage("§8" + border);
    }


    public void sendBorderedMessage(CommandSender sender, String borderChar, boolean arrows, ArrayList<String> text) {
        sendBorderedMessage(sender, borderChar, arrows, text.toArray(new String[text.size()]));
    }

    public void sendBorderedMessage(CommandSender sender, String borderChar, ArrayList<TextComponent> text) {
        if (text.size() <= 0) {
            return;
        }
        if (sender instanceof ConsoleCommandSender) {
            borderChar = "-";
        }
        String border = Strings.repeat(borderChar, 64);
        sender.sendMessage("§8" + border);
        sender.sendMessage("");
        for (TextComponent component : text) {
            sender.spigot().sendMessage(component);
        }
        sender.sendMessage("");
        sender.sendMessage("§8" + border);
    }

    public void sendBorderedMessage(HTPlayer player, String borderChar, boolean arrows, ArrayList<String> text) {
        sendBorderedMessage(player.getPlayer(), borderChar, arrows, text);
    }

    public void sendBorderedMessage(HTPlayer player, String borderChar, boolean arrows, String... text) {
        sendBorderedMessage(player.getPlayer(), borderChar, arrows, text);
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

    public TextComponent getTextPerformingCommandComponent(String message, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(message));
        command = "/" + command.replaceFirst("/", "");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return component;
    }

    public void sendTextPerformingCommand(Player player, String message, String command) {
        player.spigot().sendMessage(getTextPerformingCommandComponent(message, command));
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

    public void sendCenteredMessage(Player player, String message) {
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
            message = HackerTools.getPlugin().getPlaceholderManager().replaceString(message, player);
            player.sendMessage(message);
        } else {
            player.sendMessage(message);
        }
    }

    public void sendPlayerMessage(HTPlayer player, String message) {
        sendPlayerMessage(player.getPlayer(), message, true);
    }

    public void sendPlayerMessage(HTPlayer player, String message, boolean placeholder) {
        sendPlayerMessage(player.getPlayer(), message, placeholder);
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
        message = HackerTools.getPlugin().getPlaceholderManager().replaceString(message, Bukkit.getOfflinePlayer(specialPlayer));
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
            message = HackerTools.getPlugin().getPlaceholderManager().replaceString(message);
            sender.sendMessage(message);
        } else {
            sender.sendMessage(message);
        }
    }

    public String getDistributor() {
        return distributor;
    }

    public void broadcastSpecialMessage(SpecialMessageType type, CommandSender player, String line1, String line2) {
        broadcastSpecialMessage(type, player.getName(), line1, line2);
    }

    public void broadcastSpecialMessage(SpecialMessageType type, String player, String line1, String line2) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i <= 13; i++) {
                p.sendMessage("");
            }
            for (int i = 0; i < type.getText().length; i++) {
                String output = type.getText()[i];
                if (i == 2) {
                    output += line1;
                }
                if (i == 3) {
                    output += line2;
                }
                if (i == 5) {
                    output += " §7§o- " + player;
                }
                sendPlayerMessage(p, output);
            }
        }
    }

    public enum SpecialMessageType {
        ALERT("§4████████",
                "§4███§f██§4███",
                "§4███§f██§4███",
                "§4███§f██§4███",
                "§4███§f██§4███",
                "§4████████",
                "§4███§f██§4███ ",
                "§4████████");

        private String[] text;

        SpecialMessageType(String... text) {
            this.text = text;
        }

        public String[] getText() {
            return text;
        }
    }
}
