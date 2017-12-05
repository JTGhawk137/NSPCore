package com.nonstoppvp.core.gui;

import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import com.nonstoppvp.core.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SettingsGUI implements GUI, Listener
{

    private Inventory inventory;

    @Override
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if (e.getInventory().getName().equals("Settings Menu"))
        {
            Player p = (Player) e.getWhoClicked();
            NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());
            ItemStack item = e.getCurrentItem();

            if (item == null || item.getType() == Material.AIR)
                return;

            if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Return to menu"))
            {
                p.openInventory(player.getInventory("profile", false));
                return;
            } else if (item.equals(ItemUtils.getSettingDye(true, "censorship")))
            {
                player.changeSetting("censorship", false);
                e.getInventory().setItem(9, ItemUtils.getSettingDye(false, "censorship"));
                return;
            } else if (item.equals(ItemUtils.getSettingDye(false, "censorship")))
            {
                player.changeSetting("censorship", true);
                e.getInventory().setItem(9, ItemUtils.getSettingDye(true, "censorship"));
                return;
            } else if (item.equals(ItemUtils.getSettingDye(true, "your friend request setting")))
            {
                player.changeSetting("allowFriendRequests", false);
                e.getInventory().setItem(10, ItemUtils.getSettingDye(false, "your friend request setting"));
                return;
            } else if (item.equals(ItemUtils.getSettingDye(false, "your friend request setting")))
            {
                player.changeSetting("allowFriendRequests", true);
                e.getInventory().setItem(10, ItemUtils.getSettingDye(true, "your friend request setting"));
                return;
            }
        }
    }

    @Override
    public void createGUI(NSPPlayer player)
    {

        inventory = Bukkit.createInventory(null, 27, "Settings Menu");
        inventory.setItem(0, ItemUtils.createItem(Material.BARRIER, 1, "§aChat Censor", Arrays.asList("§7Toggles chat censor.")));
        inventory.setItem(1, ItemUtils.createItem(Material.SKULL_ITEM, 1, "§aAllow Friend Requests", Arrays.asList("§7Toggles friend requests.")));
        inventory.setItem(22, ItemUtils.createItem(Material.ARROW, 1, "§6Return to menu", Arrays.asList("§7Returns you to the main menu.")));

        if (player.getSetting("censorship"))
            inventory.setItem(9, ItemUtils.getSettingDye(true, "censorship"));
        else
            inventory.setItem(9, ItemUtils.getSettingDye(false, "censorship"));
        if (player.getSetting("allowFriendRequests"))
            inventory.setItem(10, ItemUtils.getSettingDye(true, "your friend request setting"));
        else
            inventory.setItem(10, ItemUtils.getSettingDye(false, "your friend request setting"));

    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    @Override
    public Inventory getViewableInventory()
    {
        return null;
    }

    @Override
    public void refreshInventory(NSPPlayer player)
    {

    }

    @Override
    public void refreshViewableInventory(NSPPlayer player)
    {

    }
}
