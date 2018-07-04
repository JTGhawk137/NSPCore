package com.nonstoppvp.core.profiles;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.BasicDBObject;
import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.api.GUI;
import com.nonstoppvp.core.api.events.LevelUpEvent;
import com.nonstoppvp.core.api.events.SettingChangeEvent;
import com.nonstoppvp.core.gui.FriendsGUI;
import com.nonstoppvp.core.gui.ProfileGUI;
import com.nonstoppvp.core.gui.SettingsGUI;
import com.nonstoppvp.core.gui.SocialMediaGUI;
import com.nonstoppvp.core.profiles.parties.Party;
import com.nonstoppvp.core.utils.LevelUtils;
import lombok.Data;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class NSPPlayer
{
    private UUID uuid;
    private int level;
    private int exp;
    private int orbs;
    private Party party;
    private boolean linkActive;
    private boolean isLoaded;
    private String rank;

    private List<UUID> friends = Lists.newArrayList();
    private List<UUID> requests = Lists.newArrayList();

    private Map<String, Boolean> settings = Maps.newHashMap();
    private Map<String, String> socialMedia = Maps.newHashMap();
    private Map<String, GUI> guis = Maps.newHashMap();

    private ProfileGUI profileGUI = new ProfileGUI();
    private SocialMediaGUI socialMediaGUI = new SocialMediaGUI();
    private FriendsGUI friendsGUI = new FriendsGUI();
    private SettingsGUI settingsGUI = new SettingsGUI();

    private Document document;

    public NSPPlayer(UUID uuid)
    {
        this.uuid = uuid;
    }

    public void loadGUIs()
    {
        profileGUI.createGUI(this);
        guis.put("profile", profileGUI);
        socialMediaGUI.createGUI(this);
        guis.put("socialMedia", socialMediaGUI);
        friendsGUI.createGUI(this);
        guis.put("friends", friendsGUI);
        settingsGUI.createGUI(this);
        guis.put("settings", settingsGUI);
    }

    /**
     * If you make changes to the settings, make sure to put it in here so the players can receive the update.
     */
    public void checkDocument()
    {
        Map settings = (Map) document.get("settings");
        if (!settings.containsKey("playerHide"))
            settings.put("playerHide", false);
        if (!settings.containsKey("censorship"))
            settings.put("censorship", false);
        if (!settings.containsKey("allowFriendRequests"))
            settings.put("allowFriendRequests", true);
    }

    public String getName()
    {
        return Bukkit.getPlayer(uuid).getName();
    }

    public void addExp(int exp)
    {
        this.exp += exp;
    }

    public GUI getGUI(String name)
    {
        return guis.get(name);
    }

    public Boolean getSetting(String setting)
    {
        return settings.get(setting);
    }

    public String getSocialMedia(String link)
    {
        return socialMedia.get(link);
    }

    /**
     * Gets the players gui's
     *
     * @param guiName
     * @param viewable Is the GUI we're returning the viewable version?
     * @return
     */
    public Inventory getInventory(String guiName, boolean viewable)
    {
        if (viewable)
        {
            getGUI(guiName).refreshViewableInventory(this);
            return getGUI(guiName).getViewableInventory();
        }
        getGUI(guiName).refreshInventory(this);
        return getGUI(guiName).getInventory();
    }

    public void levelUp()
    {
        if (exp >= LevelUtils.getExpForNextLevel(level))
        {
            level++;
            exp = 0;
            LevelUpEvent event = new LevelUpEvent(this);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public void changeSetting(String setting, boolean value)
    {
        SettingChangeEvent event = new SettingChangeEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled())
            settings.put(setting, value);
    }

    public List<UUID> getFriendRequests()
    {
        return requests;
    }

    public void addFriendRequest(UUID uuid)
    {
        if (settings.get("allowFriendRequests"))
            requests.add(uuid);
    }

    public void loadPlayer()
    {
        party = null;
        document = NSPCore.getInstance().getMongoManager().getPlayerDoc(this);
        checkDocument();
        level = document.getInteger("level");
        exp = document.getInteger("exp");
        orbs = document.getInteger("orbs");
        friends = (List) document.get("friends");
        settings = (Map) document.get("settings");
        socialMedia = (Map) document.get("socialmedia");
        rank = document.getString("rank");
        loadGUIs();
        setLoaded(true);
        Bukkit.getPlayer(uuid).sendMessage("Player loaded");
    }

    public void savePlayer(boolean update)
    {
        document.append("uuid", getUuid().toString());
        document.append("rank", rank);
        document.append("level", level);
        document.append("exp", exp);
        document.append("orbs", orbs);
        document.append("friends", getFriends());
        document.append("socialmedia", new BasicDBObject(getSocialMedia()));
        document.append("settings", new BasicDBObject(getSettings()));

        if (update)
            NSPCore.getInstance().getMongoManager().updatePlayerDocument(this, document);
        else
            NSPCore.getInstance().getMongoManager().savePlayerDocument(this, document);
    }
}
