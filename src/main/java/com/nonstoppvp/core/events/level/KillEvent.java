package com.nonstoppvp.core.events.level;

import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener
{

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        Player player = e.getEntity().getKiller();
        NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
        nPlayer.levelUp();
        nPlayer.addExp(50);
    }
}
