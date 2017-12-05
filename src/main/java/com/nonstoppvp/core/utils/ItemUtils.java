package com.nonstoppvp.core.utils;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class ItemUtils
{

    //Skulls
    private static ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    private static SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
    private static List<String> skullLore = Lists.newArrayList();
    //Dye
    private static ItemStack greyDye = new ItemStack(351, 1, (byte) 8);
    private static ItemStack greenDye = new ItemStack(351, 1, (byte) 10);
    private static ItemMeta greenMeta = greenDye.getItemMeta();
    private static ItemMeta greyMeta = greenDye.getItemMeta();
    private static List<String> dyeLore = Lists.newArrayList();
    //Compass
    private static ItemStack compass = new ItemStack(Material.COMPASS, 1);
    private static ItemMeta compassMeta = compass.getItemMeta();
    private static List<String> compassLore = Lists.newArrayList();
    //Emerald (Donations)
    private static ItemStack emerald = new ItemStack(Material.EMERALD, 1);
    private static ItemMeta emeraldMeta = emerald.getItemMeta();
    private static List<String> emeraldLore = Lists.newArrayList();


    /**
     * Giving the player items when they join the server
     *
     * @param player
     */
    public static void joinInventory(Player player)
    {
        NSPPlayer nPlayer = PlayerManager.getPlayer(player.getUniqueId());
        player.getInventory().clear();

        compassLore.clear();
        compassLore.add("§7Right-Click to view our Servers.");
        compassMeta.setLore(compassLore);
        compassMeta.setDisplayName("§aServer Menu");
        compass.setItemMeta(compassMeta);
        player.getInventory().setItem(0, compass);

        skullMeta.setOwner(player.getName());
        skullLore.clear();
        skullLore.add("§7Right-Click to View Profile Information.");
        skullMeta.setLore(skullLore);
        skullMeta.setDisplayName("§aMy Profile");
        skull.setItemMeta(skullMeta);
        player.getInventory().setItem(1, skull);

        if (nPlayer.getSetting("playerHide").equals(true))
            player.getInventory().setItem(7, getVisibilityDye(false));
        else
            player.getInventory().setItem(7, getVisibilityDye(true));

        emeraldLore.clear();
        emeraldLore.add("§7Right-Click to help support the server.");
        emeraldMeta.setLore(emeraldLore);
        emeraldMeta.setDisplayName("§aDonation Menu");
        emerald.setItemMeta(emeraldMeta);
        player.getInventory().setItem(8, emerald);
    }

    /**
     * Getting a skull of a player
     *
     * @param player
     * @return
     */
    public static ItemStack getPlayersSkull(NSPPlayer player)
    {
        skullMeta.setOwner(Bukkit.getPlayer(player.getUuid()).getName());
        skullLore.clear();
        skullLore.add("§7Level: §a" + player.getLevel());
        skullLore.add("§7EXP: §a" + player.getExp());
        skullLore.add("§7Orbs: §a" + player.getOrbs());
        skullMeta.setLore(skullLore);
        skullMeta.setDisplayName("§a" + Bukkit.getPlayer(player.getUuid()).getName());
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static ItemStack getPlayersSkull(Player player)
    {
        skullMeta.setOwner(player.getName());
        skullLore.clear();
        skullMeta.setLore(skullLore);
        skullMeta.setDisplayName("§a" + Bukkit.getPlayer(player.getName()));
        skull.setItemMeta(skullMeta);
        return skull;
    }

    /**
     * @param material Material Enum
     * @param amount   Amount of the item
     * @param name     Display name
     * @param lore     Item lore
     * @return
     */
    public static ItemStack createItem(Material material, int amount, String name, List<String> lore)
    {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    /**
     * Getting the skull of the player
     *
     * @param url    skin of the player
     * @param amount amount of the item
     * @param name   Display name
     * @param lore   Item Lore
     * @return
     */
    public static ItemStack getSkull(String url, int amount, String name, List<String> lore)
    {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        ItemMeta meta = head.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(name);
        if (url.isEmpty()) return head;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField;
        try
        {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1)
        {
            e1.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }

    /**
     * Getting the two basic types of dye, green and grey.
     *
     * @param isGreen
     * @return
     */
    public static ItemStack getVisibilityDye(boolean isGreen)
    {
        if (isGreen)
        {
            dyeLore.clear();
            dyeLore.add("§7Right-Click to change player visibility.");
            greenMeta.setLore(dyeLore);
            greenMeta.setDisplayName("§aPlayers: §7Visible");
            greenDye.setItemMeta(greenMeta);
            return greenDye;
        } else
        {
            dyeLore.clear();
            dyeLore.add("§7Right-Click to change player visibility.");
            greyMeta.setLore(dyeLore);
            greyMeta.setDisplayName("§aPlayers: §7Hidden");
            greyDye.setItemMeta(greyMeta);
            return greyDye;
        }
    }

    public static ItemStack getSettingDye(boolean isGreen, String settingName)
    {
        if (!isGreen)
        {
            dyeLore.clear();
            dyeLore.add("§7Right-Click to change " + settingName + ".");
            greenMeta.setLore(dyeLore);
            greenMeta.setDisplayName("§7Current: §a[ON]");
            greenDye.setItemMeta(greenMeta);
            return greenDye;
        } else
        {
            dyeLore.clear();
            dyeLore.add("§7Right-Click to change " + settingName + ".");
            greyMeta.setLore(dyeLore);
            greyMeta.setDisplayName("§7Current: §c[OFF]");
            greyDye.setItemMeta(greyMeta);
            return greyDye;
        }
    }

    public static ItemStack getItemFromConfig(FileConfiguration config, int number)
    {
        for (String str : config.getConfigurationSection("Ranks." + number).getKeys(false))
        {
            ItemStack stack = new ItemStack(Material.valueOf(config.getString("Ranks." + number + ".itemStack")));
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(config.getString("Ranks." + number + ".name".replaceAll("&", "§")));
            List<String> lore = Lists.newArrayList();
            for (String s : config.getConfigurationSection("Ranks." + number + ".lore").getKeys(false))
            {
                lore.add(config.getString(("Ranks." + number + ".lore" + s).replaceAll("&", "§")));
            }
            stack.setItemMeta(meta);
            return stack;
        }
        return null;
    }
}
