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

public class SocialMediaGUI implements GUI, Listener
{

    private Inventory inventory;
    private Inventory viewable;

    @EventHandler
    @Override
    public void onClick(InventoryClickEvent e)
    {
        if (e.getInventory().getName().equals("Social Media") || e.getInventory().getName().contains("'s Social Media"))
        {
            Player p = (Player) e.getWhoClicked();
            NSPPlayer player = PlayerManager.getPlayer(p.getUniqueId());
            ItemStack item = e.getCurrentItem();

            if (item == null || item.getType() == Material.AIR)
                return;

            if (e.getInventory().getName().equals("Social Media") && !item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Return to menu")
                    && !item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Return"))
            {
                p.closeInventory();
                player.setLinkActive(true);
                p.sendMessage("§7Paste your " + item.getItemMeta().getDisplayName() + " §7link in chat:");
                return;
            } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Return to menu"))
            {
                p.openInventory(player.getInventory("profile", false));
                return;
            } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Return"))
            {
                NSPPlayer player2 = PlayerManager.getPlayer(Bukkit.getPlayer(e.getInventory().getTitle().substring(0, e.getInventory().getTitle().indexOf("'") - 1)).getUniqueId());
                p.openInventory(player2.getInventory("profile", true));
                return;
            }
        }
    }

    @Override
    public void createGUI(NSPPlayer player)
    {
        inventory = Bukkit.createInventory(null, 18, "Social Media");
        inventory.setItem(2, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aTwitter", Arrays.asList("§6Click to edit your Twitter link.", "§7Current link: §a" + player.getSocialMedia("twitter"))));
        inventory.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce",
                1, "§aYouTube", Arrays.asList("§6Click to edit your YouTube link.", "§7Current link: §a" + player.getSocialMedia("youtube"))));
        inventory.setItem(4, ItemUtils.getSkull("http://textures.minecraft.net/texture/ac88d6163fabe7c5e62450eb37a074e2e2c88611c998536dbd8429faa0819453",
                1, "§aInstagram", Arrays.asList("§6Click to edit your Instagram link.", "§7Current link: §a" + player.getSocialMedia("instagram"))));
        inventory.setItem(5, ItemUtils.getSkull("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda",
                1, "§aDiscord", Arrays.asList("§6Click to edit your Discord link.", "§7Current link: §a" + player.getSocialMedia("discord"))));
        inventory.setItem(6, ItemUtils.getSkull("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f",
                1, "§aTwitch", Arrays.asList("§6Click to edit your Twitch link.", "§7Current link: §a" + player.getSocialMedia("twitch"))));
        inventory.setItem(13, ItemUtils.createItem(Material.ARROW, 1, "§6Return to menu", Arrays.asList("§7Returns you to the main menu.")));

        viewable = Bukkit.createInventory(null, 18, player.getName() + "'s Social Media");
        viewable.setItem(2, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aTwitter", Arrays.asList("§a" + player.getSocialMedia("twitter"))));
        viewable.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce",
                1, "§aYouTube", Arrays.asList("§a" + player.getSocialMedia("youtube"))));
        viewable.setItem(4, ItemUtils.getSkull("http://textures.minecraft.net/texture/ac88d6163fabe7c5e62450eb37a074e2e2c88611c998536dbd8429faa0819453",
                1, "§aInstagram", Arrays.asList("§a" + player.getSocialMedia("instagram"))));
        viewable.setItem(5, ItemUtils.getSkull("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda",
                1, "§aDiscord", Arrays.asList("§a" + player.getSocialMedia("discord"))));
        viewable.setItem(6, ItemUtils.getSkull("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f",
                1, "§aTwitch", Arrays.asList("§a" + player.getSocialMedia("twitch"))));
        viewable.setItem(13, ItemUtils.createItem(Material.ARROW, 1, "§6Return", Arrays.asList("§7Returns you to the previous gui.")));

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
        inventory.setItem(2, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aTwitter", Arrays.asList("§6Click to edit your Twitter link.", "§7Current link: §a" + player.getSocialMedia("twitter"))));
        inventory.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce",
                1, "§aYouTube", Arrays.asList("§6Click to edit your YouTube link.", "§7Current link: §a" + player.getSocialMedia("youtube"))));
        inventory.setItem(4, ItemUtils.getSkull("http://textures.minecraft.net/texture/ac88d6163fabe7c5e62450eb37a074e2e2c88611c998536dbd8429faa0819453",
                1, "§aInstagram", Arrays.asList("§6Click to edit your Instagram link.", "§7Current link: §a" + player.getSocialMedia("instagram"))));
        inventory.setItem(5, ItemUtils.getSkull("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda",
                1, "§aDiscord", Arrays.asList("§6Click to edit your Discord link.", "§7Current link: §a" + player.getSocialMedia("discord"))));
        inventory.setItem(6, ItemUtils.getSkull("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f",
                1, "§aTwitch", Arrays.asList("§6Click to edit your Twitch link.", "§7Current link: §a" + player.getSocialMedia("twitch"))));
    }

    @Override
    public void refreshViewableInventory(NSPPlayer player)
    {
        viewable.setItem(2, ItemUtils.getSkull("http://textures.minecraft.net/texture/3685a0be743e9067de95cd8c6d1ba21ab21d37371b3d597211bb75e43279",
                1, "§aTwitter", Arrays.asList("§7Current link: §a" + player.getSocialMedia("twitter"))));
        viewable.setItem(3, ItemUtils.getSkull("http://textures.minecraft.net/texture/d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce",
                1, "§aYouTube", Arrays.asList("§7Current link: §a" + player.getSocialMedia("youtube"))));
        viewable.setItem(4, ItemUtils.getSkull("http://textures.minecraft.net/texture/ac88d6163fabe7c5e62450eb37a074e2e2c88611c998536dbd8429faa0819453",
                1, "§aInstagram", Arrays.asList("§7Current link: §a" + player.getSocialMedia("instagram"))));
        viewable.setItem(5, ItemUtils.getSkull("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda",
                1, "§aDiscord", Arrays.asList("§7Current link: §a" + player.getSocialMedia("discord"))));
        viewable.setItem(6, ItemUtils.getSkull("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f",
                1, "§aTwitch", Arrays.asList("§7Current link: §a" + player.getSocialMedia("twitch"))));
    }
}