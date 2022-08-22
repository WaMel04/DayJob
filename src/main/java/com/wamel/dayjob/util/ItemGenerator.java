package com.wamel.dayjob.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

public class ItemGenerator {

    public static ItemStack create(Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        return item;
    }

    public static ItemStack create(Material material, int data, int amount) {
        ItemStack item = new ItemStack(material, amount, (byte) data);
        return item;
    }

    public static ItemStack create(Material material, String name, String lore, int amount) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        List lores = Arrays.asList(lore.split("\\|\\|"));

        meta.setDisplayName(name);
        if(!(lore.equalsIgnoreCase("")))
            meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(Material material, String name, String lore, int data, int amount) {
        ItemStack item = new ItemStack(material, 1);
        item.setData(new MaterialData(material, (byte) data));
        ItemMeta meta = item.getItemMeta();
        List lores = Arrays.asList(lore.split("\\|\\|"));

        meta.setDisplayName(name);
        if(!(lore.equalsIgnoreCase("")))
            meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
