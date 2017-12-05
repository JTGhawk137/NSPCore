package com.nonstoppvp.core.gui;

import com.nonstoppvp.core.profiles.NSPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FriendsGUI implements GUI, Listener
{

    private Inventory inventory;

    @Override
    public void onClick(InventoryClickEvent e)
    {

    }

    @Override
    public void createGUI(NSPPlayer player)
    {
        inventory = Bukkit.createInventory(null, 54, "Friends List");

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
