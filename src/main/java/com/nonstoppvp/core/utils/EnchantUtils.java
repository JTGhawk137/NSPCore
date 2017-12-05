package com.nonstoppvp.core.utils;

import com.google.common.collect.Maps;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EnchantUtils
{

    private Map<String, Enchantment> enchantments = Maps.newHashMap();
    private Enchantment fakeEnchant;

    public void createEnchant()
    {
        fakeEnchant = new FakeEnchant(100);
        enchantments.put("fake", fakeEnchant);
        try
        {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(fakeEnchant);
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    public void handleShutdown()
    {
        try
        {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

            for (Enchantment e : enchantments.values())
            {
                if (byId.containsKey(e.getId()))
                    byId.remove(e.getId());
                if (byName.containsKey(e.getName()))
                    byName.remove(e.getName());
            }
        } catch (Exception ignored)
        {
        }
    }

    public class FakeEnchant extends Enchantment
    {

        public FakeEnchant(int id)
        {
            super(id);
        }

        @Override
        public String getName()
        {
            return null;
        }

        @Override
        public int getMaxLevel()
        {
            return 0;
        }

        @Override
        public int getStartLevel()
        {
            return 0;
        }

        @Override
        public EnchantmentTarget getItemTarget()
        {
            return null;
        }

        @Override
        public boolean isTreasure()
        {
            return false;
        }

        @Override
        public boolean isCursed()
        {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment enchantment)
        {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack itemStack)
        {
            return false;
        }
    }
}
