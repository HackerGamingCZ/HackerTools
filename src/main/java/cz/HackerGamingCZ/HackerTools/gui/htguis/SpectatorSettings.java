package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.actions.PerformCommand;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpectatorSettings implements GUI{

    @Override
    public int getInventorySize() {
        return 27;
    }

    @Override
    public String getInventoryName() {
        return "§lSettings of spectator mode";
    }

    @Override
    public ArrayList<Item> getItems(Player player) {
        ArrayList<Item> items = new ArrayList<>();
        for(int i = 1; i <= 3;i++){
            if(player == null){
                items.add(new Item("speed"+i,11+i, HackerTools.getPlugin().getItemManager().createItem(Material.FEATHER, "§cSpeed "+i, false), new ItemInInventoryClickEvent(new PerformCommand("ht speed "+i, true)), true, true));
                continue;
            }
            items.add(new Item("speed"+i,11+i, HackerTools.getPlugin().getItemManager().createItem(Material.FEATHER, "§cSpeed "+i, player.getWalkSpeed() == HackerTools.getPlugin().getMechanics().getRealMoveSpeed(i)), new ItemInInventoryClickEvent(new PerformCommand("ht speed "+i, true)), true, true));
        }
        return items;
    }

}