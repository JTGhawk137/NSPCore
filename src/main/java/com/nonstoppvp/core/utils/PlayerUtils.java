package com.nonstoppvp.core.utils;

import com.mongodb.BasicDBObject;
import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.profiles.NSPPlayer;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.Map;

public class PlayerUtils
{

    public static void handleJoin(final NSPPlayer player)
    {
        player.setLoaded(false);
        Bukkit.getScheduler().runTaskAsynchronously(NSPCore.getInstance(), () ->
        {
            if (!NSPCore.getInstance().getMongoManager().doesPlayerExist(player))
            {
                Map<String, Boolean> settings = player.getSettings();
                Map<String, String> socialMedia = player.getSocialMedia();
                Document document = new Document();
                settings.put("playerHide", false);
                settings.put("censorship", false);
                settings.put("allowFriendRequests", true);
                socialMedia.put("twitter", "None");
                socialMedia.put("youtube", "None");
                socialMedia.put("instagram", "None");
                socialMedia.put("twitch", "None");
                socialMedia.put("discord", "None");
                player.setLevel(1);
                player.setExp(0);
                player.setOrbs(0);
                player.setRank("");
                document.append("uuid", player.getUuid().toString());
                document.append("level", player.getLevel());
                document.append("exp", player.getExp());
                document.append("orbs", player.getOrbs());
                document.append("friends", player.getFriends());
                document.append("socialmedia", new BasicDBObject(socialMedia));
                document.append("settings", new BasicDBObject(settings));

                NSPCore.getInstance().getMongoManager().createPlayerDocument(player, document);
                ItemUtils.joinInventory(Bukkit.getPlayer(player.getUuid()));
                player.loadGUIs();
                player.setLoaded(true);
                return;
            } else
            {
                player.loadPlayer();
                ItemUtils.joinInventory(Bukkit.getPlayer(player.getUuid()));
            }
        });
    }
}
