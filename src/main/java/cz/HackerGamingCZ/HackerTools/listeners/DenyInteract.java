package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class DenyInteract implements Listener {

    private ArrayList<Material> materials = new ArrayList<>();

    public DenyInteract(Material... materials) {
        Collections.addAll(this.materials, materials);
    }

    @EventHandler
    public void onIteract(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null){
            return;
        }
        if (!HackerTools.getPlugin().getItemManager().isItemLegit(new ItemStack(e.getClickedBlock().getType()))) {
            return;
        }
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            return;
        }
        Material material = e.getClickedBlock().getType();
        if (material == Material.AIR) {
            return;
        }
        if (materials.contains(material)) {
            e.setCancelled(true);
            e.setUseInteractedBlock(Event.Result.DENY);
        }
    }
}
