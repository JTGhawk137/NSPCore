package com.nonstoppvp.core.gui;

import com.nonstoppvp.core.api.GUI;
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

public class ProfileGUI implements GUI, Listener
{

    private Inventory inventory;
    private Inventory viewable;

    @Override
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if (e.getInventory().getName().equals("Profile Menu") || e.getInventory().getName().contains("'s Profile"))
        {
            Player p = (Player) e.getWhoClicked();
            NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());
            ItemStack item = e.getCurrentItem();

            if (item == null || item.getType() == Material.AIR)
                return;

            if (item.getItemMeta().getDisplayName().equals("§aSettings"))
            {
                p.openInventory(player.getInventory("settings", false));
                return;
            }
            if (item.getItemMeta().getDisplayName().equals("§aSocial Media") && e.getInventory().getName().equals("Profile Menu"))
            {
                p.openInventory(player.getInventory("socialMedia", false));
                return;
            } else if (item.getItemMeta().getDisplayName().equals("§aSocial Media") && e.getInventory().getName().contains("'s Profile"))
            {
                NSPPlayer player2 = PlayerManager.getPlayer(Bukkit.getPlayer(e.getInventory().getTitle().substring(0, e.getInventory().getTitle().indexOf("'") - 1)).getUniqueId());
                p.openInventory(player2.getInventory("socialMedia", true));
                return;
            }
        }
    }

    @Override
    public void createGUI(NSPPlayer player)
    {
        inventory = Bukkit.createInventory(null, 9, "Profile Menu");
        inventory.setItem(2, ItemUtils.getPlayersSkull(player));
        inventory.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aSocial Media", Arrays.asList("§7Click to edit your Social Media links.")));
        inventory.setItem(4, ItemUtils.createItem(Material.PAPER, 1, "§aStats", Arrays.asList("§7Click to view your Stats.")));
        inventory.setItem(5, ItemUtils.createItem(Material.DIAMOND, 1, "§aAchievements", Arrays.asList("§cCurrently unavailable.")));
        inventory.setItem(6, ItemUtils.createItem(Material.REDSTONE_COMPARATOR, 1, "§aSettings", Arrays.asList("§7Click to edit your Settings.")));

        viewable = Bukkit.createInventory(null, 9, player.getName() + "'s Profile");
        viewable.setItem(2, ItemUtils.getPlayersSkull(player));
        viewable.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aSocial Media", Arrays.asList("§7Click to view " + player.getName() + "'s social media.")));
        viewable.setItem(4, ItemUtils.createItem(Material.PAPER, 1, "§aStats", Arrays.asList("§7Click to view " + player.getName() + "'s Stats.")));
        viewable.setItem(5, ItemUtils.createItem(Material.DIAMOND, 1, "§aAchievements", Arrays.asList("§cCurrently unavailable.")));
        viewable.setItem(6, ItemUtils.createItem(Material.SKULL_ITEM, 1, "§aAdd Friend", Arrays.asList("§7Click to add " + player.getName() + " to your friends list.")));

    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    @Override
    public Inventory getViewableInventory()
    {
        return viewable;
    }

    @Override
    public void refreshInventory(NSPPlayer player)
    {
        inventory.setItem(2, ItemUtils.getPlayersSkull(player));
    }

    @Override
    public void refreshViewableInventory(NSPPlayer player)
    {
        viewable.setItem(2, ItemUtils.getPlayersSkull(player));
    }
}