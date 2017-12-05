package com.nonstoppvp.core.events;

import com.nonstoppvp.core.language.MessageEnum;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener
{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());
        if (!player.isLoaded())
        {
            p.sendMessage(MessageEnum.LOADING.getMessage());
            e.setCancelled(true);
            return;
        }
        if (player.isLinkActive())
        {
            e.setCancelled(true);
            String message = e.getMessage();
            if (message.contains("https://twitter.com/"))
            {
                player.getSocialMedia().put("twitter", e.getMessage());
                p.sendMessage("§aYou have successfully updated your Twitter.");
                player.setLinkActive(false);
                return;
            } else if (message.contains("https://www.youtube.com/channel/"))
            {
                player.getSocialMedia().put("youtube", e.getMessage());
                p.sendMessage("§aYou have successfully updated your YouTube.");
                player.setLinkActive(false);
                return;
            } else if (message.contains("https://www.instagram.com/"))
            {
                player.getSocialMedia().put("instagram", e.getMessage());
                p.sendMessage("§aYou have successfully updated your Instagram.");
                player.setLinkActive(false);
                return;
            } else if (message.contains("#"))
            {
                player.getSocialMedia().put("discord", e.getMessage());
                p.sendMessage("§aYou have successfully updated your Discord.");
                player.setLinkActive(false);
                return;
            } else if (message.contains("https://go.twitch.tv/"))
            {
                player.getSocialMedia().put("twitch", e.getMessage());
                p.sendMessage("§aYou have successfully updated your Twitch.");
                player.setLinkActive(false);
                return;
            } else
            {
                p.sendMessage("§cYour link is invalid.");
                player.setLinkActive(false);
                return;
            }
        }
    }
}
