package cz.HackerGamingCZ.HackerTools.gui.htguis;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.items.Item;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.actions.Teleport;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;
import cz.HackerGamingCZ.HackerTools.events.ItemInInventoryClickEvent;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
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
    public ArrayList<Item> getItems(Player player) {
        ArrayList<Item> items = new ArrayList<>();
        int i = 0;
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (HackerTools.getPlugin().getPlayerManager().getPlayer(p).isSpectator()) {
                continue;
            }
            String lore = HackerTools.getPlugin().getPlaceholderAPI().replaceSpecialPlaceholder(HackerTools.getPlugin().getPlaceholderAPI().replaceString(Lang.TELEPORT_TO_PLAYER), Placeholder.PLAYERNAME, p.getName());
            items.add(new Item(i, HackerTools.getPlugin().getItemManager().createHeadItem(p, "§a" + p.getName(), false, "", lore), new ItemInInventoryClickEvent(new Teleport(p)), true, true));
            i++;
        }
        return items;
    }
}
