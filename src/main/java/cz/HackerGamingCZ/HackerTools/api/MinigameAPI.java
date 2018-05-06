package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.enums.Placeholder;
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

public class MinigameAPI {

    private int maxPlayers;
    private int minPlayers;
    private GameState gameState;
    private ArrayList<Player> spectators = new ArrayList<>();
    private int defaultCountdown = 60;
    private int countdown = defaultCountdown;
    private int force = 15;
    private Location spectLocation;

    public void setupGame(int minPlayers, int maxPlayers, GameState state, Location spectLocation){
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.spectLocation = spectLocation;
        setGameState(state);
    }

    public void setupGame(int minPlayers, int maxPlayers, GameState state, Location spectLocation, int countdownDefault, int force){
        setupGame(minPlayers, maxPlayers, state, spectLocation);
        this.countdown = countdownDefault;
        this.defaultCountdown = countdownDefault;
        this.force = force;
    }

    public Location getSpectLocation() {
        return spectLocation;
    }

    public void resetPlayer(Player player, GameMode gameMode, boolean health, boolean food, boolean inventory, boolean armor, ItemStack[] ignoredItems){
        if(gameMode != null){
            player.setGameMode(gameMode);
        }
        if(health){
            player.setHealth(20);
        }
        if(food){
            player.setFoodLevel(20);
        }
        ArrayList<ItemStack> ignoreItemsList = new ArrayList<>();
        if(ignoredItems != null){
            Collections.addAll(ignoreItemsList, ignoredItems);
        }
        if(inventory){
            for(ItemStack is : player.getInventory()){
                if(is == null || is.getType() != Material.AIR || ignoreItemsList.contains(is)){
                    continue;
                }
                player.getInventory().removeItem(is);
            }
        }
        if(armor){
            for(ItemStack is : player.getEquipment().getArmorContents()){
                if(is == null || is.getType() != Material.AIR || ignoreItemsList.contains(is)){
                    continue;
                }
                player.getInventory().removeItem(is);
            }
        }
    }

    public boolean isServerInLobby(){
        return HackerTools.getPlugin().getMinigameAPI().getGameState() == GameState.WAITING || HackerTools.getPlugin().getMinigameAPI().getGameState() == GameState.STARTING;
    }

    public void startLobbyCountdown(){
        setGameState(GameState.STARTING);
        for(Player player : Bukkit.getOnlinePlayers()) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.COUNTDOWN_START_INFO);
        }
        HackerTools.getPlugin().getSchedulerAPI().addScheduler(SchedulerAPI.SchedulerType.LOBBY, Bukkit.getScheduler().scheduleSyncRepeatingTask(HackerTools.getPlugin(), ()->{
            if(countdown == 0){
                HackerTools.getPlugin().getTitleAPI().sendTitle(Bukkit.getOnlinePlayers(), 5, 40, 5, "§aStart!", "§c");
                for(Player player : Bukkit.getOnlinePlayers()) {
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.GAME_START_INFO);
                }
                stopLobbyCoutdown(CountdownEndEvent.EndCause.SCHEDULER_END);
                setGameState(GameState.INGAME);
                return;
            }
            if(countdown % 10 == 0 || countdown <= 10){
                for(Player player : Bukkit.getOnlinePlayers()){
                    String message = Lang.COUNTDOWN_INFO;
                    message = HackerTools.getPlugin().getPlaceholderAPI().replaceSpecialPlaceholder(message, Placeholder.COUNTDOWN, String.valueOf(countdown));
                    HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, message);
                }
            }
            if(countdown <= 5){
                HackerTools.getPlugin().getTitleAPI().sendTitle(Bukkit.getOnlinePlayers(), 3, 15, 3, "§c", "§c"+String.valueOf(countdown));
            }
            countdown--;
            CountdownUpdateEvent event = new CountdownUpdateEvent(countdown, SchedulerAPI.SchedulerType.LOBBY, CountdownUpdateEvent.UpdateCause.SCHEDULER_CYCLE);
            Bukkit.getPluginManager().callEvent(event);
        }, 20L, 20L));
    }

    public void force(){
        if(isServerInLobby()){
            if(HackerTools.getPlugin().getSchedulerAPI().getScheduler(SchedulerAPI.SchedulerType.LOBBY) == -1){
                startLobbyCountdown();
            }
            setCountdown(force);
        }
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
        CountdownUpdateEvent event = new CountdownUpdateEvent(countdown, SchedulerAPI.SchedulerType.LOBBY, CountdownUpdateEvent.UpdateCause.FORCE);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void stopLobbyCoutdown(CountdownEndEvent.EndCause cause){
        countdown = defaultCountdown;
        if(cause == CountdownEndEvent.EndCause.PLAYER_DISCONNECT){
            for(Player player : Bukkit.getOnlinePlayers()) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, Lang.COUNTDOWN_STOP_PLAYERDISCONNECT_INFO);
            }
        }
        CountdownEndEvent event = new CountdownEndEvent(cause, SchedulerAPI.SchedulerType.LOBBY);
        Bukkit.getPluginManager().callEvent(event);
        HackerTools.getPlugin().getSchedulerAPI().stopSchedulder(SchedulerAPI.SchedulerType.LOBBY);
    }

    public void setSpectator(Player player, Location location, boolean compass, boolean settings){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(p);
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        if(location != null) {
            player.teleport(location);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
        if(compass){
            HackerTools.getPlugin().getItemInteractAPI().getItemByIdentificator("spectatelist").giveItem(player.getInventory(), 8);
        }
        if(settings){
            HackerTools.getPlugin().getItemInteractAPI().getItemByIdentificator("spectsettings").giveItem(player.getInventory(), 4);
        }
        spectators.add(player);
    }

    public void setSpectator(Player player, Location location){
        setSpectator(player, location, true, true);
    }

    public boolean isSpectator(Player player){
        return spectators.contains(player);
    }

    public void removeSpectator(Player player){
        spectators.remove(player);
    }

    public void resetPlayer(Player player){
        resetPlayer(player, GameMode.ADVENTURE, true, true, true, true, null);
    }


    public int getMaxPlayers() { return maxPlayers; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) {
        MinecraftServer.getServer().setMotd(gameState.getMotd());
        this.gameState = gameState;
    }
    public int getMinPlayers() { return minPlayers; }

    public int getCountdown() {
        return countdown;
    }
}
