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

public class ProfileCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (!(commandSender instanceof Player))
            return true;
        Player player = (Player) commandSender;
        if (!player.hasPermission(PermissionsEnum.VIEW_PROFILE.getPermission()))
        {
            player.sendMessage(MessageEnum.PERMISSION.getMessage());
            return true;
        }
        if (args.length != 1)
        {
            player.sendMessage("§6Usage: §7/profile <username>");
            return true;
        }
        try
        {
            Player target = Bukkit.getPlayer(args[0]);
            NSPPlayer nPlayer = PlayerManager.getPlayer(target.getUniqueId());
            player.openInventory(nPlayer.getInventory("profile", true));
            return true;
        } catch (NullPointerException e)
        {
            player.sendMessage("§cCouldn't find player!");
        }
        return true;
    }
}
