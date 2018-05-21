package cz.HackerGamingCZ.HackerTools.managers;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import cz.HackerGamingCZ.HackerTools.events.CountdownEndEvent;
import cz.HackerGamingCZ.HackerTools.events.CountdownUpdateEvent;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;

public class MinigameManager {

    private int maxPlayers = 0;
    private int minPlayers = 0;
    private GameState gameState;
    private int defaultCountdown = 60;
    private int countdown = defaultCountdown;
    private int force = 15;
    private Location spectLocation;
    private Location lobbyLocation;
    private GameMode defaultGameMode;

    public MinigameManager() {
        setGameState(GameState.SETUP);
    }

    public void setupGame(int minPlayers, int maxPlayers, Location spectLocation, Location lobbyLocation, GameMode defaultGameMode) {
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.spectLocation = spectLocation;
        this.lobbyLocation = lobbyLocation;
        if (defaultGameMode == null) {
            throw new IllegalArgumentException("Default Gamemode cannot be null!");
        }
        this.defaultGameMode = defaultGameMode;
        setGameState(GameState.WAITING);
    }

    public void setupGame(int minPlayers, int maxPlayers, Location spectLocation, Location lobbyLocation, GameMode defaultGameMode, int countdownDefault, int force) {
        this.countdown = countdownDefault;
        this.defaultCountdown = countdownDefault;
        this.force = force;
        setupGame(minPlayers, maxPlayers, spectLocation, lobbyLocation, defaultGameMode);
    }

    public Location getSpectLocation() {
        return spectLocation;
    }

    public void enableReconnect() {
        GameState.INGAME.setJoinType(GameState.JoinType.RECONNECT);
    }

    public void disableReconnect() {
        GameState.INGAME.setJoinType(GameState.JoinType.SPECTATOR);
    }

    public void resetPlayer(Player player, GameMode gameMode, boolean health, boolean food, boolean inventory, boolean armor, boolean potionEffects, ItemStack... ignoredItems) {
        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.1F);
        if (potionEffects) {
            for (PotionEffect potionEffect : player.getActivePotionEffects()) {
                player.removePotionEffect(potionEffect.getType());
            }
        }
        if (gameMode != null) {
            player.setGameMode(gameMode);
        }
        if (health) {
            player.setHealth(20);
        }
        if (food) {
            player.setFoodLevel(20);
        }
        ArrayList<ItemStack> ignoreItemsList = new ArrayList<>();
        if (ignoredItems != null) {
            Collections.addAll(ignoreItemsList, ignoredItems);
        }
        if (inventory) {
            for (ItemStack is : player.getInventory()) {
                if (is == null || is.getType() == Material.AIR || ignoreItemsList.contains(is)) {
                    continue;
                }
                player.getInventory().removeItem(is);
            }
        }
        if (armor) {
            player.getEquipment().clear();
        }
    }

    public boolean isServerInLobby() {
        return HackerTools.getPlugin().getMinigameManager().getGameState() == GameState.WAITING || HackerTools.getPlugin().getMinigameManager().getGameState() == GameState.STARTING;
    }

    public void startLobbyCountdown() {
        if (gameState == GameState.SETUP) {
            return;
        }
        if (!isServerInLobby()) {
            return;
        }
        setGameState(GameState.STARTING);
        for (Player player : Bukkit.getOnlinePlayers()) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.COUNTDOWN_START_INFO);
        }
        HackerTools.getPlugin().getSchedulerManager().addScheduler(SchedulerManager.SchedulerType.LOBBY, Bukkit.getScheduler().scheduleSyncRepeatingTask(HackerTools.getPlugin(), () -> {
            if (countdown == 0) {
                HackerTools.getPlugin().getTitleManager().sendTitle(Bukkit.getOnlinePlayers(), 5, 40, 5, "§aStart!", "§c");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.GAME_START_INFO);
                }
                stopLobbyCoutdown(CountdownEndEvent.EndCause.SCHEDULER_END);
                setGameState(GameState.INGAME);
                return;
            }
            if (countdown % 10 == 0 || countdown <= 10) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String message = Lang.COUNTDOWN_INFO;
                    message = HackerTools.getPlugin().getPlaceholderAPI().replaceSpecialPlaceholder(message, Placeholder.COUNTDOWN, String.valueOf(countdown));
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, message);
                }
            }
            if (countdown <= 5) {
                HackerTools.getPlugin().getTitleManager().sendTitle(Bukkit.getOnlinePlayers(), 3, 15, 3, "§c", "§c" + String.valueOf(countdown));
            }
            countdown--;
            CountdownUpdateEvent event = new CountdownUpdateEvent(countdown, SchedulerManager.SchedulerType.LOBBY, CountdownUpdateEvent.UpdateCause.SCHEDULER_CYCLE);
            Bukkit.getPluginManager().callEvent(event);
        }, 20L, 20L));
    }

    public void force() {
        if (isServerInLobby()) {
            if (HackerTools.getPlugin().getSchedulerManager().getScheduler(SchedulerManager.SchedulerType.LOBBY) == -1) {
                startLobbyCountdown();
            }
            setCountdown(force);
        }
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
        CountdownUpdateEvent event = new CountdownUpdateEvent(countdown, SchedulerManager.SchedulerType.LOBBY, CountdownUpdateEvent.UpdateCause.FORCE);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void stopLobbyCoutdown(CountdownEndEvent.EndCause cause) {
        countdown = defaultCountdown;
        if (cause == CountdownEndEvent.EndCause.PLAYER_DISCONNECT) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.COUNTDOWN_STOP_PLAYERDISCONNECT_INFO);
            }
        }
        CountdownEndEvent event = new CountdownEndEvent(cause, SchedulerManager.SchedulerType.LOBBY);
        Bukkit.getPluginManager().callEvent(event);
        HackerTools.getPlugin().getSchedulerManager().stopSchedulder(SchedulerManager.SchedulerType.LOBBY);
    }

    public void resetPlayer(Player player) {
        resetPlayer(player, defaultGameMode, true, true, true, true, true);
    }


    public int getMaxPlayers() {
        return maxPlayers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        MinecraftServer.getServer().setMotd(gameState.getMotd());
        this.gameState = gameState;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getCountdown() {
        return countdown;
    }
}
