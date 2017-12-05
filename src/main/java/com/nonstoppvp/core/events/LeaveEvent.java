package com.nonstoppvp.core.events;

import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener
{

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());
        player.savePlayer(true);
        player.unloadPlayer();
        PlayerManager.removePlayer(player);
    }
}
