package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpectatorSettings implements GUI {

    @Override
    public int getInventorySize() {
        return 27;
    }

    @Override
    public String getInventoryName() {
        return "§lSettings of spectator mode";
    }

    @Override
    public ArrayList<Item> getItems(HTPlayer player) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            items.add(new Item("speed" + i, 11 + i, HackerTools.getPlugin().getItemManager().createItem(Material.FEATHER, "§cSpeed " + i, player.getPlayer().getWalkSpeed() == HackerTools.getPlugin().getMechanics().getRealMoveSpeed(i)), p -> {
                p.getPlayer().setWalkSpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(finalI));
                p.getPlayer().setFlySpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(finalI));
            }, true, true));
        }
        return items;
    }

}