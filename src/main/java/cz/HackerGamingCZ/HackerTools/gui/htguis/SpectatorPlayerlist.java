package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpectatorPlayerlist implements GUI {


    @Override
    public int getInventorySize() {
        return 54;
    }

    @Override
    public String getInventoryName() {
        return "§lSpectate";
    }

    @Override
    public ArrayList<Item> getItems(HTPlayer player) {
        ArrayList<Item> items = new ArrayList<>();
        int i = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(p);
            if (htPlayer == null) {
                continue;
            }
            if (htPlayer.isSpectator()) {
                continue;
            }
            String lore = HackerTools.getPlugin().getPlaceholderManager().replaceString(HackerTools.getPlugin().getPlaceholderManager().replaceString(Lang.TELEPORT_TO_PLAYER), p);
            items.add(new Item(i, HackerTools.getPlugin().getItemManager().createHeadItem(p, "§a" + p.getName(), false, "", lore), player1 -> player1.getPlayer().teleport(player.getPlayer()), true, true));
            i++;
        }
        return items;
    }
}
