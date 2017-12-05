package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.effect.HeartEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Hearts
{

    public static void playHeartEffect(Entity entity, ParticleEffect particleEffect)
    {
        if (entity instanceof Player)
        {
            if (!((Player) entity).isOnline() || !ParticleManager.particlesActive.containsKey(entity.getUniqueId().toString()))
            {
                Bukkit.getScheduler().cancelTask(ParticleManager.particlesActive.get(entity.getUniqueId().toString()));
                return;
            }
        }
        HeartEffect heartEffect = new HeartEffect(NSPCore.getInstance().getEffectManager());
        heartEffect.setEntity(entity);
        heartEffect.particle = particleEffect;
        heartEffect.iterations = 1 * 20;
        heartEffect.relativeOffset = new Vector(0, .30, 0);
        //heartEffect.pitch = entity.getLocation().getPitch();
        heartEffect.start();
    }

    public static void loopHeartEffect(Entity entity, ParticleEffect particleEffect)
    {
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(NSPCore.getInstance(), () ->
        {
            playHeartEffect(entity, particleEffect);
        }, 0, 20);
        ParticleManager.particlesActive.put(entity.getUniqueId().toString(), task);
    }

}
