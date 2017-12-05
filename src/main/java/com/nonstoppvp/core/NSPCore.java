package com.nonstoppvp.core;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.nonstoppvp.core.commands.FriendCommand;
import com.nonstoppvp.core.commands.OrbsCommand;
import com.nonstoppvp.core.commands.ProfileCommand;
import com.nonstoppvp.core.commands.SetSpawnCommand;
import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.events.EventHandler;
import com.nonstoppvp.core.gui.DonationGUI;
import com.nonstoppvp.core.networking.MongoManager;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;
import com.nonstoppvp.core.utils.EnchantUtils;
import de.slikey.effectlib.EffectManager;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class NSPCore extends JavaPlugin
{

    private static NSPCore instance;
    private static DonationGUI donationGUI = new DonationGUI();
    private PlayerManager playerManager;
    private FileManager fileManager = new FileManager();
    private MongoManager mongoManager = new MongoManager();
    private ProtocolManager protocolManager;
    private EventHandler eventHandler = new EventHandler();
    private EnchantUtils enchantUtils = new EnchantUtils();
    private EffectManager em = new EffectManager(this);

    //API's
    private LuckPermsApi api;
    private Optional<LuckPermsApi> check;

    public static NSPCore getInstance()
    {
        return instance;
    }

    public static DonationGUI getDonationGUI()
    {
        return donationGUI;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        playerManager = new PlayerManager();
        fileManager.loadConfigs();
        mongoManager.setupMongo();
        protocolManager = ProtocolLibrary.getProtocolManager();
        donationGUI.createGUI();
        api = LuckPerms.getApi();
        check = LuckPerms.getApiSafe();
        enchantUtils.createEnchant();
        eventHandler.registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable()
    {
        for (NSPPlayer player : PlayerManager.getPlayers())
        {
            player.savePlayer(false);
            player.unloadPlayer();
        }
        enchantUtils.handleShutdown();
        instance = null;
        em.dispose();
        playerManager.removeAll();
    }

    public void registerCommands()
    {
        if (FileManager.get("settings.yml").getBoolean("friends"))
            getCommand("friends").setExecutor(new FriendCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("orbs").setExecutor(new OrbsCommand());
        getCommand("profile").setExecutor(new ProfileCommand());
    }

    public MongoManager getMongoManager()
    {
        return mongoManager;
    }

    public LuckPermsApi getLuckPermsAPI()
    {
        return api;
    }

    public ProtocolManager getProtocolManager()
    {
        return protocolManager;
    }

    public EffectManager getEffectManager()
    {
        return em;
    }
}
