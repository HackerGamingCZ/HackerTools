package cz.HackerGamingCZ.HackerTools.events;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChatMessageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private HTPlayer player;
    private boolean cancelled;
    private String message;
    private String prefix = "";
    private String suffix = "";

    public PlayerChatMessageEvent(HTPlayer player, String message) {
        this.player = player;
        this.message = message;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HTPlayer getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
