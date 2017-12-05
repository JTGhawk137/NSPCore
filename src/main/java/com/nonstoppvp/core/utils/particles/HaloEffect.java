package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class HaloEffect
{


    public static void playHaloEffect(Entity entity, ParticleEffect particleEffect)
    {
        CircleEffect haloEffect = new CircleEffect(NSPCore.getInstance().getEffectManager());
        if (entity instanceof Player)
        {
            if (!((Player) entity).isOnline() || !ParticleManager.particlesActive.containsKey(entity.getUniqueId().toString()))
            {
                Bukkit.getScheduler().cancelTask(ParticleManager.particlesActive.get(entity.getUniqueId().toString()));
                return;
            }
        }
        haloEffect.setEntity(entity);
        haloEffect.enableRotation = false;
        haloEffect.ySubtract = -.30;
        haloEffect.particles = 10;
        haloEffect.radius = .5f;
        haloEffect.particle = particleEffect;
        haloEffect.iterations = 1 * 20;
        haloEffect.start();
        return;
    }

    public static void loopHaloEffect(Entity entity, ParticleEffect particleEffect)
    {
        int task =
                Bukkit.getScheduler().scheduleSyncRepeatingTask(NSPCore.getInstance(), () ->
                {
                    playHaloEffect(entity, particleEffect);
                }, 0, 2 * 20);
        ParticleManager.particlesActive.put(entity.getUniqueId().toString(), task);
    }

}
