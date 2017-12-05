package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FrostLord
{

    public static void setEffect(final Entity entity)
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
            double t = 0;

            public void run()
            {
                // 12ish seconds
                t += Math.PI / 16;
                double x, y, z;
                Location location = entity.getLocation();

                for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 5)
                {
                    x = 0.2 * (4 * Math.PI - t) * Math.cos(t + phi);  //Radius
                    y = 0.2 * t;   //Vertical
                    z = 0.2 * (4 * Math.PI - t) * Math.sin(t + phi);  //Radius
                    location.add(x, y, z);
                    ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, 0, 1, location, 16);
                    location.subtract(x, y, z);

                    if (t >= 4 * Math.PI)
                    {
                        location.add(x, y, z);
                        ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, 0, 10, location, 16);
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer(NSPCore.getInstance(), 0, 1);
    }

    public static void loopEffect(Entity entity)
    {
        int task =
                Bukkit.getScheduler().scheduleSyncRepeatingTask(NSPCore.getInstance(), () ->
                {
                    setEffect(entity);
                }, 0, 4 * 20);
        ParticleManager.particlesActive.put(entity.getUniqueId().toString(), task);

    }

}