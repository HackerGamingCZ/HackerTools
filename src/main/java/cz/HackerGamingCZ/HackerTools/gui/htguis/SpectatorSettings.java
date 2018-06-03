package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class SpectatorSettings implements GUI {

    @Override
    public boolean closeInventoryAfterInteract() {
        return true;
    }

    @Override
    public int getInventorySize() {
        return 27;
    }

    @Override
    public String getInventoryName() {
        return "§lSettings of spectator mode";
    }

    @Override
    public HashMap<Integer, ItemBuilder> getItems(HTPlayer player) {
        HashMap<Integer, ItemBuilder> items = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            items.put(11 + i, new ItemBuilder(Material.FEATHER)
                    .setDisplayName("§eSpeed §6" + i)
                    .setGlowing(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(i) == player.getPlayer().getFlySpeed())
                    .setPlayerAction(player1 -> {
                        player1.getPlayer().setFlySpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(finalI));
                        player1.getPlayer().setWalkSpeed(HackerTools.getPlugin().getMechanics().getRealMoveSpeed(finalI));
                    }));

        }
        return items;
    }

}