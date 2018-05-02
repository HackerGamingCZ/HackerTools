package cz.HackerGamingCZ.HackerTools.api;

import cz.HackerGamingCZ.HackerTools.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class MinigameAPI {

    private int maxPlayers;
    private int minPlayers;
    private GameState gameState;

    public void setupGame(int maxPlayers, int minPlayers, GameState state){
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.gameState = state;
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

    public void resetPlayer(Player player){
        resetPlayer(player, GameMode.ADVENTURE, true, true, true, true, null);
    }


    public int getMaxPlayers() { return maxPlayers; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) { this.gameState = gameState; }
    public int getMinPlayers() { return minPlayers; }
}
