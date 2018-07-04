package com.nonstoppvp.core.api.events;

import com.nonstoppvp.core.profiles.NSPPlayer;
import lombok.Data;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Data
public class SettingChangeEvent extends Event
{

    private static final HandlerList HANDLERS = new HandlerList();
    private NSPPlayer player;
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