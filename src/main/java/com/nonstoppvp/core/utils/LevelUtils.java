package com.nonstoppvp.core.utils;

public class LevelUtils
{

    private static final double constant = (3 / 2) * 100;

    public static double getExpForNextLevel(int level)
    {
        return (level * level) * (constant);
    }
}
