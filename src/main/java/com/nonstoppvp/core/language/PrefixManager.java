package com.nonstoppvp.core.language;

import org.bukkit.ChatColor;

public class PrefixManager {

    private final String PREFIX = "NSP";

    public String info(String message) {
        return ChatColor.GOLD + PREFIX + " » " + ChatColor.RESET + message;
    }

    public String broadcast(String message) {
        return ChatColor.GOLD + PREFIX + " » " + ChatColor.AQUA + message;
    }

    public String success(String message) {
        return ChatColor.GREEN + PREFIX + " » " + ChatColor.RESET + message;
    }

    public String fail(String message) {
        return ChatColor.RED + PREFIX + " » " + ChatColor.RESET + message;
    }

    public String warning(String message) {
        return ChatColor.YELLOW + PREFIX + " » " + ChatColor.RESET + message;
    }

    public String severe(String message) {
        return ChatColor.DARK_RED + PREFIX + " » " + ChatColor.RED + message;
    }

}