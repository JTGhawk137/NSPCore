package com.nonstoppvp.core.events;

import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import com.nonstoppvp.core.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener
{

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (FileManager.get("settings.yml").get("hub").equals(true))
        {
            NSPPlayer nPlayer = new NSPPlayer(player.getUniqueId());
            PlayerUtils.handleJoin(nPlayer);
            PlayerManager.addPlayer(nPlayer);
        }
    }
}
