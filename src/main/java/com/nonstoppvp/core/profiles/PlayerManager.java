package com.nonstoppvp.core.profiles;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerManager
{

    private static List<NSPPlayer> players = Lists.newArrayList();
    private static Map<UUID, NSPPlayer> playersMap = Maps.newHashMap();

    public static List<NSPPlayer> getPlayers()
    {
        return players;
    }

    public static NSPPlayer getPlayer(UUID uuid)
    {
        return playersMap.get(uuid);
    }

    public static void addPlayer(NSPPlayer player)
    {
        players.add(player);
        playersMap.put(player.getUuid(), player);
    }

    public static void removePlayer(NSPPlayer player)
    {
        players.remove(player);
        playersMap.remove(player.getUuid());
    }

    public void removeAll()
    {
        players.clear();
        playersMap.clear();
    }
}
