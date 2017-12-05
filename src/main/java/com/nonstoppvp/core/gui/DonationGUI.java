package com.nonstoppvp.core.gui;

import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class DonationGUI implements Listener
{

    private Inventory inventory;

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if (e.getInventory().getName().equals("Donation Ranks"))
        {
            ItemStack item = e.getCurrentItem();

            if (item == null || item.getType() == Material.AIR)
                return;

            e.getWhoClicked().closeInventory();
            e.getWhoClicked().sendMessage("§6Buycraft Link: §7" + FileManager.get("settings.yml").get("buycraft"));
            return;
        }
    }

    public void createGUI()
    {
        inventory = Bukkit.createInventory(null, 9, "Donation Ranks");
        inventory.setItem(0, ItemUtils.createItem(Material.COAL, 1, "Rank1", Arrays.asList("Ranklore")));
    }

    public Inventory getInventory()
    {
        return inventory;
    }
}
