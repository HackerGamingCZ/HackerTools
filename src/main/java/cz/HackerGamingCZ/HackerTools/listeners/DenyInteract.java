package cz.HackerGamingCZ.HackerTools.listeners;

import cz.HackerGamingCZ.HackerTools.Main;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class DenyInteract implements Listener {

    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<String> ignoredPlayers = new ArrayList<>();

    public DenyInteract(Material[] materials, String[] ignoredPlayers){
        Collections.addAll(this.materials, materials);
        Collections.addAll(this.ignoredPlayers, ignoredPlayers);
    }

    @EventHandler
    public void onIteract(PlayerInteractEvent e){
        if(ignoredPlayers.contains(e.getPlayer().getName())){
            return;
        }
        if(!Main.getPlugin().getItemManager().isItemLegit(new ItemStack(e.getClickedBlock().getType()))){
            return;
        }
        Material material = e.getClickedBlock().getType();
        if(material == Material.AIR){
            return;
        }
        if(materials.contains(material)){
            e.setCancelled(true);
            e.setUseInteractedBlock(Event.Result.DENY);
        }
    }
}
