package cz.HackerGamingCZ.HackerTools.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class EconomyManager {

    private HashMap<Player, Long> accounts = new HashMap<>();
    private String name;
    private ChatColor prefix;

    public EconomyManager(String name, ChatColor prefix){
        this.name = name;
        this.prefix = prefix;
    }
    public void newAccount(Player player, long amount){
        accounts.put(player, amount);
    }

    public void newAccount(Player player){
        accounts.put(player, (long)0);
    }
    public String getName() {
        return name;
    }
    public long getPlayersBalance(Player player){
        return accounts.getOrDefault(player, (long)0);
    }
    public boolean hasBalance(Player player, long amount){
        long balance = accounts.getOrDefault(player, (long)0);
        return balance >= amount;
    }
    public void removeBalance(Player player, long amount){
        accounts.replace(player, accounts.getOrDefault(player, (long)0)-amount);
    }
    public void addBalance(Player player, long amount){
        accounts.replace(player, accounts.getOrDefault(player, (long)0)+amount);
    }
    public void setBalance(Player player, long amount){
        accounts.replace(player, amount);
    }
    public String getFullName(){
        return prefix+getName();
    }
    public String getFullPluralName(){
        return prefix+getName()+"s";
    }
    public String getFullName(Player player){
        return accounts.getOrDefault(player, (long)0)+""+prefix+getName();
    }
    public String getFullPluralName(Player player){
        return accounts.getOrDefault(player, (long)0)+""+prefix+getName()+"s";
    }
}
