package com.nonstoppvp.core.events;

import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.language.MessageEnum;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import com.nonstoppvp.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener
{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if (FileManager.get("settings.yml").get("hub").equals(false))
            return;
        Action a = e.getAction();
        ItemStack item = e.getItem();
        if (a.equals(Action.PHYSICAL) || item == null || item.getType().equals(Material.AIR))
        {
            return;
        }

        Player p = e.getPlayer();
        NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());

        if (!player.isLoaded())
        {
            p.sendMessage(MessageEnum.LOADING.getMessage());
            e.setCancelled(true);
            return;
        }

        if (item.getType().equals(Material.SKULL_ITEM))
        {
            p.openInventory(player.getInventory("profile", false));
            return;
        } else if (item.getType().equals(Material.getMaterial(351)))
        {
            if (player.getSetting("playerHide").equals(false))
            {
                player.changeSetting("playerHide", true);
                p.sendMessage("All players have been hidden.");
                p.getInventory().setItem(7, ItemUtils.getVisibilityDye(false));
            } else
            {
                player.changeSetting("playerHide", false);
                p.sendMessage("All players have been un-hidden.");
                p.getInventory().setItem(7, ItemUtils.getVisibilityDye(true));
            }
        } else if (item.getType().equals(Material.EMERALD))
        {
            p.openInventory(NSPCore.getDonationGUI().getInventory());
            return;
        }
    }
}
