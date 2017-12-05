package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class HelixEffect
{

    public static void coneEffect(final Location location, final int red, final int green, final int blue)
    {
        new BukkitRunnable()
        {
            double phi = 0;

            public void run()
            {
                phi = phi + Math.PI / 8;
                double x, y, z;

                for (double t = 0; t <= 2 * Math.PI; t = t + Math.PI / 16)
                {
                    for (double i = 0; i <= 1; i = i + 1)
                    {
                        x = -0.4 * (2 * Math.PI - t) * 0.5 * cos(t + phi + i * Math.PI);
                        y = -0.5 * t;
                        z = -0.4 * (2 * Math.PI - t) * 0.5 * sin(t + phi + i * Math.PI);
                        location.add(x, y, z);
                        ParticleEffect.REDSTONE.display(location, Color.fromRGB(red, green, blue), 7);
                        location.subtract(x, y, z);
                    }

                }

                if (phi > 2 * Math.PI)
                {
                    this.cancel();
                }
            }
        }.runTaskTimer(NSPCore.getInstance(), 0, 3);
    }

    public static void playerConeEffect(final Entity entity, final int red, final int green, final int blue)
    {
        if (entity instanceof Player)
        {
            if (!((Player) entity).isOnline() || !ParticleManager.particlesActive.containsKey(entity.getUniqueId().toString()))
            {
                Bukkit.getScheduler().cancelTask(ParticleManager.particlesActive.get(entity.getUniqueId().toString()));
                return;
            }
        }
        new BukkitRunnable()
        {
            double phi = 0;

            public void run()
            {
                phi = phi + Math.PI / 8;
                double x, y, z;
                Location location = entity.getLocation();

                for (double t = 0; t <= 2 * Math.PI; t = t + Math.PI / 16)
                {
                    for (double i = 0; i <= 1; i = i + 1)
                    {
                        x = 0.4 * (2 * Math.PI - t) * 0.5 * cos(t + phi + i * Math.PI);
                        y = 0.5 * t;
                        z = 0.4 * (2 * Math.PI - t) * 0.5 * sin(t + phi + i * Math.PI);
                        location.add(x, y, z);
                        ParticleEffect.REDSTONE.display(location, Color.fromRGB(red, green, blue), 13);
                        location.subtract(x, y, z);
                    }

                }

                if (phi > 10 * Math.PI)
                {
                    this.cancel();
                }
            }
        }.runTaskTimer(NSPCore.getInstance(), 0, 3);
    }

    public static void loopHelixEffect(Entity entity, int red, int green, int blue)
    {
        int task =
                Bukkit.getScheduler().scheduleSyncRepeatingTask(NSPCore.getInstance(), () ->
                {
                    playerConeEffect(entity, red, green, blue);
                }, 0, 4 * 20);
        ParticleManager.particlesActive.put(entity.getUniqueId().toString(), task);
    }

}
