package com.nonstoppvp.core.events.protocolevents;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.profiles.NSPPlayer;
import com.nonstoppvp.core.profiles.PlayerManager;

public class CensorEvent
{

    public void onCensor()
    {
        // TODO: Just a test, need to actually finish this doe!
        NSPCore.getInstance().getProtocolManager().addPacketListener(new PacketAdapter(NSPCore.getInstance(),
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT)
        {
            @Override
            public void onPacketReceiving(PacketEvent event)
            {
                NSPPlayer player = PlayerManager.getPlayer(event.getPlayer().getUniqueId());
                if (event.getPacketType() == PacketType.Play.Client.CHAT && player.getSetting("censorship"))
                {
                    PacketContainer packet = event.getPacket();
                    String message = packet.getStrings().read(0);
                    if (message.contains(""))
                    {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Bad manners!");
                    }
                }
            }
        });
    }
}
