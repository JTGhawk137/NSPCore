package com.nonstoppvp.core.events;

import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.event.EventBus;
import me.lucko.luckperms.api.event.user.track.UserDemoteEvent;
import me.lucko.luckperms.api.event.user.track.UserPromoteEvent;
import org.bukkit.Bukkit;

public class LuckPermsEvents
{

    private final NSPCore plugin;

    public LuckPermsEvents(NSPCore plugin, LuckPermsApi api)
    {
        this.plugin = plugin;
        EventBus eventBus = api.getEventBus();
        eventBus.subscribe(UserPromoteEvent.class, this::onPromote);
        eventBus.subscribe(UserDemoteEvent.class, this::onDemote);
    }

    public void onPromote(UserPromoteEvent e)
    {
        NSPPlayer player = PlayerManager.getPlayer(e.getUser().getUuid());
        player.setRank(e.getGroupTo().get());
        Bukkit.getPlayer(e.getUser().getUuid()).sendMessage("§7Your rank has been set to: §a" + e.getGroupTo());
    }

    public void onDemote(UserDemoteEvent e)
    {
        NSPPlayer player = PlayerManager.getPlayer(e.getUser().getUuid());
        player.setRank(e.getGroupTo().get());
        Bukkit.getPlayer(e.getUser().getUuid()).sendMessage("§cYou've been demoted to: " + e.getGroupTo());
    }
}
