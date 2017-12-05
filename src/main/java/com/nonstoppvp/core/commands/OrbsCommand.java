package com.nonstoppvp.core.commands;

import com.nonstoppvp.core.language.PermissionsEnum;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OrbsCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if (sender.hasPermission(PermissionsEnum.GIVE_ORBS.getPermission()))
            {
                if (args.length == 1 && args[0].equalsIgnoreCase("help"))
                {
                    sender.sendMessage("§6Orbs:");
                    sender.sendMessage("§a/Orbs give <player> <amount>");
                    sender.sendMessage("§a/Orbs remove <player> <amount>");
                    sender.sendMessage("§a/Orbs set <player> <amount>");
                    return true;
                }
                if (args.length == 3)
                {
                    if (args[0].equalsIgnoreCase("give"))
                    {
                        Player player = Bukkit.getPlayer(args[1]);
                        int amount = Integer.parseInt(args[2]);
                        NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                        nPlayer.setOrbs(nPlayer.getOrbs() + amount);
                        sender.sendMessage("§a" + amount + " §7Orbs have been added to §a" + player.getName() + "'s §7Account");
                        return true;
                    } else if (args[0].equalsIgnoreCase("remove"))
                    {
                        Player player = Bukkit.getPlayer(args[1]);
                        int amount = Integer.parseInt(args[2]);
                        NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                        nPlayer.setOrbs(nPlayer.getOrbs() - amount);
                        sender.sendMessage("§a" + amount + " §7Orbs have been removed to §a" + player.getName() + "'s §7Account");
                        return true;
                    } else if (args[0].equalsIgnoreCase("set"))
                    {
                        Player player = Bukkit.getPlayer(args[1]);
                        int amount = Integer.parseInt(args[2]);
                        NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                        nPlayer.setOrbs(amount);
                        sender.sendMessage("§a" + player.getName() + "'s §7Orbs have been set to §a" + amount);
                        return true;
                    }
                }
            }
            if (sender.hasPermission(PermissionsEnum.VIEW_ORBS.getPermission()))
            {
                if (args.length == 1 && !args[0].equalsIgnoreCase("help"))
                {
                    Player player = Bukkit.getPlayer(args[0]);
                    NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                    sender.sendMessage("§7" + player.getName() + " has§a " + nPlayer.getOrbs() + "§7 Orbs");
                    return true;
                }
            }

            if (args.length == 0)
            {
                if (!(sender instanceof Player))
                    return true;
                Player player = (Player) sender;
                NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
                player.sendMessage("§7You currently have §a" + nPlayer.getOrbs() + " §7Orbs");
            }
        } catch (NullPointerException e)
        {
            sender.sendMessage("§cPlayer not found!");
        }
        return true;
    }
}