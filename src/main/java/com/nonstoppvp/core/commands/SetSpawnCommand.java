package com.nonstoppvp.core.commands;

import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.language.MessageEnum;
import com.nonstoppvp.core.language.PermissionsEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetSpawnCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (!(commandSender instanceof Player))
            return true;
        Player player = (Player) commandSender;
        if (player.hasPermission(PermissionsEnum.SET_SPAWN.getPermission()))
        {
            FileManager.get("settings.yml").set("spawn.x", player.getLocation().getX());
            FileManager.get("settings.yml").set("spawn.y", player.getLocation().getY());
            FileManager.get("settings.yml").set("spawn.z", player.getLocation().getZ());
            FileManager.get("settings.yml").set("spawn.yaw", player.getLocation().getYaw());
            FileManager.get("settings.yml").set("spawn.pitch", player.getLocation().getPitch());
            player.sendMessage(MessageEnum.SETSPAWN.getMessage());
            return true;
        }
        return true;
    }
}
