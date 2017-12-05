package com.nonstoppvp.core.commands;

import com.nonstoppvp.core.language.MessageEnum;
import com.nonstoppvp.core.language.PermissionsEnum;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FriendCommand implements CommandExecutor
{
    //TODO: Add a GUI for this as well.
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (!(commandSender instanceof Player))
            return true;
        Player player = (Player) commandSender;
        if (!player.hasPermission(PermissionsEnum.FRIENDS.getPermission()))
        {
            player.sendMessage(MessageEnum.PERMISSION.getMessage());
            return true;
        }
        if (args.length == 0 || args.length > 2)
        {
            player.sendMessage("§cPlease use /friends help to get a list of commands");
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help"))
        {
            player.sendMessage("§6Friends:");
            player.sendMessage("§a/friend add <name> - Adds a friend");
            player.sendMessage("§a/friend remove <name> - Removes a friend");
            player.sendMessage("§a/friend requests - Gets all of your friend requests");
            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("add"))
        {
            try
            {
                NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                NSPPlayer friend = PlayerManager.getPlayer(Bukkit.getPlayer(args[1].toString()).getUniqueId());

                if (nPlayer.getFriends().contains(friend.getUuid()))
                {
                    player.sendMessage("§c" + friend.getName() + " is already in your friends list!");
                    return true;
                }
                friend.getFriendRequests().add(nPlayer.getUuid());
                player.sendMessage("§aYou have sent a friend request to " + friend.getName() + "!");
                return true;
            } catch (NullPointerException e)
            {
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("remove"))
        {
            try
            {
                NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                Player friend = Bukkit.getPlayer(args[1].toString());
                if (!nPlayer.getFriends().contains(friend.getUniqueId()))
                {
                    player.sendMessage("§c" + friend.getName() + " is not in your friends list!");
                    return true;
                }
                nPlayer.getFriends().remove(friend.getUniqueId());
                player.sendMessage("§aYou have remove " + friend.getName() + " to your friends list!");
            } catch (NullPointerException e)
            {
            }
            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("requests"))
        {
            try
            {
                NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                if (nPlayer.getFriendRequests().isEmpty())
                {
                    player.sendMessage("§cYou have no new friend requests");
                    return true;
                } else
                {
                    player.sendMessage("§aFriend Requests:");
                    for (UUID uuid : nPlayer.getFriendRequests())
                    {
                        player.sendMessage("§7" + Bukkit.getPlayer(uuid));
                    }
                }
            } catch (NullPointerException e)
            {
            }
            return true;
        }
        return true;
    }
}
