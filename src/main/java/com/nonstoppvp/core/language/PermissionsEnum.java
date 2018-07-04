package com.nonstoppvp.core.language;

public enum PermissionsEnum
{
    SET_SPAWN("nsp.setspawn"),
    FRIENDS("nsp.friends"),
    VIEW_PROFILE("nsp.viewprofile"),
    VIEW_ORBS("nsp.vieworbs"),
    GIVE_ORBS("nsp.giveorbs");

    private String permission;

    PermissionsEnum(String permission)
    {
        this.permission = permission;
    }

    public String getPermission()
    {
        return permission;
    }
}
