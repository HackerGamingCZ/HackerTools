package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.PlayerAction;
import cz.HackerGamingCZ.HackerTools.builder.ItemBuilder;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        if (action == Action.PHYSICAL) {
            return;
        }
        Player player = e.getPlayer();
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        if (htPlayer == null) {
            return;
        }
        ItemStack is = e.getItem();
        if (is == null) {
            return;
        }
        if (!is.getItemMeta().hasDisplayName()) {
            return;
        }
        ItemBuilder item = HackerTools.getPlugin().getItemInteractManager().getItemByIs(is);
        if (item == null) {
            return;
        }

        PlayerAction itemAction = item.getPlayerAction();
        if (itemAction == null) {
            return;
        }
        itemAction.execute(htPlayer);
    }

}
