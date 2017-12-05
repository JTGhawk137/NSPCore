package de.slikey.effectlib.math;

import org.bukkit.configuration.ConfigurationSection;

public interface Transform {
    void load(ConfigurationSection parameters);

    double get(double t);
}
