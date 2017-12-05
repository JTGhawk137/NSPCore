package com.nonstoppvp.core.events;

import com.google.common.collect.Lists;
import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.events.level.KillEvent;
import com.nonstoppvp.core.gui.*;
import org.bukkit.event.Listener;

import java.util.List;

public class EventHandler
{

    private List<Listener> events = Lists.newArrayList();

    public void registerEvents()
    {
        events.add(new ChatEvent());
        events.add(new ClickEvent());
        events.add(new CommandEvent());
        events.add(new InteractEvent());
        events.add(new JoinEvent());
        events.add(new LeaveEvent());
        events.add(new PickupEvent());
        events.add(new KillEvent());
        events.add(new DropEvent());
        //GUI Events
        events.add(new FriendsGUI());
        events.add(new ProfileGUI());
        events.add(new SettingsGUI());
        events.add(new SocialMediaGUI());
        events.add(new StatsGUI());
        events.add(new DonationGUI());

        new LuckPermsEvents(NSPCore.getInstance(), NSPCore.getInstance().getLuckPermsAPI());

        for (Listener event : events)
        {
            NSPCore.getInstance().getServer().getPluginManager().registerEvents(event, NSPCore.getInstance());
        }
    }
}
