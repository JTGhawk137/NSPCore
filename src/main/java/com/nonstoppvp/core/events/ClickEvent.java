package com.nonstoppvp.core.events;

import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.language.MessageEnum;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener
{

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR)
            return;
        if (FileManager.get("settings.yml").get("hub").equals(false))
            return;
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());

        if (!player.isLoaded())
        {
            p.sendMessage(MessageEnum.LOADING.getMessage());
            e.setCancelled(true);
            return;
        }
    }
}
