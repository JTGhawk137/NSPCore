package com.nonstoppvp.core.profiles.parties;

import com.google.common.collect.Lists;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;

import java.util.List;
import java.util.UUID;

public class Party
{

    private UUID owner;
    private List<UUID> players = Lists.newArrayList();

    public Party(UUID owner)
    {
        this.owner = owner;
    }

    public void disbandParty()
    {
        owner = null;
        players = null;
    }

    public UUID getOwner()
    {
        return owner;
    }

    public NSPPlayer getOwner(UUID uuid)
    {
        return PlayerManager.getPlayer(uuid);
    }

    public List<UUID> getMembers()
    {
        return players;
    }
}
