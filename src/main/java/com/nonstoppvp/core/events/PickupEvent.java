package com.nonstoppvp.core.events;

import com.nonstoppvp.core.config.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupEvent implements Listener
{

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e)
    {
        if (FileManager.get("settings.yml").get("hub").equals(true))
            e.setCancelled(true);
    }
}
