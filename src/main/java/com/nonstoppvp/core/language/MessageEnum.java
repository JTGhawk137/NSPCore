package com.nonstoppvp.core.language;

public enum MessageEnum
{
    PERMISSION("§cYou do not have permission to use this."),
    LOADING("§cPlease wait until your profile is loaded."),
    SETSPAWN("§aYou've set the spawn.");

    String message;

    MessageEnum(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
