package com.nonstoppvp.core.gui;

import com.nonstoppvp.core.api.GUI;
import com.nonstoppvp.core.profiles.NSPPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class StatsGUI implements GUI, Listener
{
    @Override
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {

    }

    @Override
    public void createGUI(NSPPlayer player)
    {

    }

    @Override
    public Inventory getInventory()
    {
        return null;
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
