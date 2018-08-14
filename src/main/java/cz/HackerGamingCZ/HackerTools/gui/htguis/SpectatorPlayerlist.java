package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import cz.HackerGamingCZ.HackerTools.builder.SkullBuilder;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SpectatorPlayerlist implements GUI {


    @Override
    public boolean closeInventoryAfterInteract() {
        return true;
    }

    @Override
    public int getInventorySize() {
        return 54;
    }

    @Override
    public String getInventoryName() {
        return "§lSpectate";
    }

    @Override
    public HashMap<Integer, ItemBuilder> getItems(HTPlayer player) {
        HashMap<Integer, ItemBuilder> items = new HashMap<>();
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
            items.put(i, new SkullBuilder()
                    .setOwner(p.getName())
                    .setDisplayName("§a" + p.getName())
                    .addLore("")
                    .addLore(lore)
                    .setPlayerAction(player1 -> player1.getPlayer().teleport(htPlayer.getPlayer())));
            i++;
        }
        return items;
    }
}
