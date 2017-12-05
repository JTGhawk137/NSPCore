package com.nonstoppvp.core.api.events;

import com.nonstoppvp.core.profiles.NSPPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SettingChangeEvent extends Event
{

    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private NSPPlayer player;
    @Setter
    @Getter
    private boolean cancelled;

    public SettingChangeEvent(NSPPlayer player)
    {
        this.player = player;
    }

    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }
}