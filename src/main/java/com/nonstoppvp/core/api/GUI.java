package com.nonstoppvp.core.api;

import com.nonstoppvp.core.profiles.NSPPlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface GUI
{

    /**
     * Click event for each inventory
     *
     * @param e
     */
    void onClick(InventoryClickEvent e);

    /**
     * Creates the GUI for the user and the viewable version
     *
     * @param player
     */
    void createGUI(NSPPlayer player);

    /**
     * Get the players version of the inventory
     *
     * @return
     */
    Inventory getInventory();

    /**
     * Inventory that other players will see when they want to view things such as social media.
     * Return null if the inventory doesn't have a viewable section.
     *
     * @return
     */
    Inventory getViewableInventory();

    /**
     * Refreshes the inventory before the player opens it to make sure everything is updated.
     *
     * @param player
     */
    void refreshInventory(NSPPlayer player);

    /**
     * Refreshes the inventory before a viewer opens it to make sure everything is updated.
     *
     * @param player
     */
    void refreshViewableInventory(NSPPlayer player);
}
