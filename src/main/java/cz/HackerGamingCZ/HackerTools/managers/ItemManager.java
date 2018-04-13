package cz.HackerGamingCZ.HackerTools.managers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class ItemManager {

    public boolean isItemLegit(ItemStack is, boolean hasItemMeta, String displayName, String[] lore, boolean isEnchanted){
        boolean hasDisplayName = displayName != null;
        boolean hasLore = lore != null && lore.length != 0;
        if(is == null){
            return false;
        }
        if((hasDisplayName || hasItemMeta || hasLore) && !is.hasItemMeta()){
            return false;
        }
        if(isEnchanted){
            int enchants = 0;
            enchants += is.getEnchantments().size();
            if(is.hasItemMeta()){
                enchants += is.getItemMeta().getEnchants().size();
            }
            if(enchants == 0){
                return false;
            }
        }
        ItemMeta im = is.getItemMeta();
        if(hasDisplayName && (!im.hasDisplayName() && im.getDisplayName().equals(displayName))){
            return false;
        }
        if(hasLore && (im.getLore().size() == 0 || im.getLore().size() != lore.length)){
            return false;
        }
        if(hasLore){
            ArrayList<String> loreList = new ArrayList<>();
            Collections.addAll(loreList, lore);
            for(int i = 0; i < im.getLore().size(); i++){
                if(!loreList.get(i).equals(im.getLore().get(i))){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isItemLegit(ItemStack is){
        return is != null;
    }

    public boolean isItemLegit(ItemStack is, String displayName){
        if(is == null){
            return false;
        }
        if(!is.hasItemMeta()){
            return false;
        }
        ItemMeta im = is.getItemMeta();
        return im.hasDisplayName() && im.getDisplayName().equals(displayName);
    }


}
