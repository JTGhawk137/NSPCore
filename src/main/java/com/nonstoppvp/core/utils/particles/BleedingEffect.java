package com.nonstoppvp.core.utils.particles;

import com.nonstoppvp.core.NSPCore;
import de.slikey.effectlib.effect.BleedEffect;
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Entity;

public class BleedingEffect
{


    public static void playBleadingEffect(Entity player)
    {
        CircleEffect bleedEffect = new CircleEffect(NSPCore.getInstance().getEffectManager());
        //BleedEffect bleedEffect = new BleedEffect(effectManager);
        bleedEffect.setEntity(player);
        bleedEffect.enableRotation = false;
        bleedEffect.ySubtract = -.30;
        bleedEffect.particles = 10;
        bleedEffect.radius = .5f;
        bleedEffect.particle = ParticleEffect.VILLAGER_HAPPY;
        //bleedEffect.autoOrient = true;
        // Add a callback to the effect
        bleedEffect.callback = new Runnable()
        {

            @Override
            public void run()
            {
            }

        };
        // Bleeding takes 15 seconds
        // period * iterations = time of effect
        bleedEffect.iterations = 10 * 20;
        bleedEffect.start();
    }

    public static void playBleedingEffect(Entity player)
    {
        BleedEffect bleedEffect = new BleedEffect(NSPCore.getInstance().getEffectManager());
        bleedEffect.setLocation(player.getLocation());
        bleedEffect.particleCount = 500;
        bleedEffect.iterations = 1;
        bleedEffect.start();
    }

}
