package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RadialWave
{

    public static void setEffect(Entity entity)
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
            double t = Math.PI / 4;
            double r = 2;
            Location loc = entity.getLocation();

            public void run()
            {
                t = t + 0.1 * Math.PI;
                for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32)
                {
                    double x = t * Math.cos(theta);
                    double y = 2 * Math.exp(-0.1 * t) * Math.sin(t) + 1.5;
                    double z = t * Math.sin(theta);
                    loc.add(x, y, z);
                    ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, loc, 16);
                    loc.subtract(x, y, z);

                    theta = theta + Math.PI / 64;

                    x = t * Math.cos(theta);
                    y = 2 * Math.exp(-0.1 * t) * Math.sin(t) + 1.5;
                    z = t * Math.sin(theta);
                    loc.add(x, y, z);
                    ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 1, loc, 16);
                    loc.subtract(x, y, z);
                }
                if (t > 20)
                {
                    this.cancel();
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