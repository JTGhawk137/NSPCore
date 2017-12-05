package com.nonstoppvp.core.utils.particles;

import org.bukkit.entity.Entity;

import java.util.HashMap;

public class ParticleManager
{

    public static HashMap<String, Integer> particlesActive = new HashMap<>();

    public static void clearEffects(Entity entity)
    {
        particlesActive.remove(entity.getUniqueId().toString());
    }

}
