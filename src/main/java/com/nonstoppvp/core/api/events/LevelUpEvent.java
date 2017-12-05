package com.nonstoppvp.core.api.events;

import com.nonstoppvp.core.profiles.NSPPlayer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent extends Event
{

    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private NSPPlayer player;

    public LevelUpEvent(NSPPlayer player)
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