package com.wamel.dayjob.job;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Block;
import com.wamel.dayjob.data.DataManager$Config;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.util.ItemGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;

public class Job$Miner implements Listener {

    private static final DayJob plugin = DayJob.getInstance();

    private static final String JOB_CODE = "Miner";

    private static final int EMERALD_EXP = 5000;
    private static final int DIAMOND_EXP = 1000;
    private static final int GOLD_EXP = 250;
    private static final int IRON_EXP = 100;
    private static final int REDSTONE_EXP = 125;
    private static final int LAPIS_EXP = 25;
    private static final int COAL_EXP = 25;

    private static final int JOB_DROP_CHANCE = 20;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.isCancelled())
            return;

        switch(event.getBlock().getType()) {
            case EMERALD_ORE:
            case DIAMOND_ORE:
            case GOLD_ORE:
            case IRON_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case COAL_ORE:
                break;
            default:
                return;
        }

        DataManager$Block.blockData.put(event.getBlock().getLocation(), true);
        return;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled())
            return;

        int exp = 0;

        switch(event.getBlock().getType()) {
            case EMERALD_ORE:
                exp = EMERALD_EXP;
                break;
            case DIAMOND_ORE:
                exp = DIAMOND_EXP;
                break;
            case GOLD_ORE:
                exp = GOLD_EXP;
                break;
            case IRON_ORE:
                exp = IRON_EXP;
                break;
            case REDSTONE_ORE:
                exp = REDSTONE_EXP;
                break;
            case LAPIS_ORE:
                exp = LAPIS_EXP;
                break;
            case COAL_ORE:
                exp = COAL_EXP;
                break;
            default:
                return;
        }

        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        Location loc = event.getBlock().getLocation();

        if(DataManager$Block.blockData.get(loc) != null) {
            DataManager$Block.blockData.remove(loc);
            return;
        }
        if(!(DataManager$Player.getJob(uuid).equalsIgnoreCase(JOB_CODE)))
            return;

        DataManager$Player.addExp(player, exp * DataManager$Config.EXP_BONUS);


        Random random = new Random();
        if(random.nextInt(100) < JOB_DROP_CHANCE) {
            switch(event.getBlock().getType()) {
                case EMERALD_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.EMERALD_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.EMERALD, 2));
                    break;
                case DIAMOND_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.DIAMOND_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.DIAMOND, 2));
                    break;
                case GOLD_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.GOLD_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.GOLD_INGOT, 2));
                    break;
                case IRON_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.IRON_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.IRON_INGOT, 2));
                    break;
                case REDSTONE_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.REDSTONE_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.REDSTONE, 2));
                    break;
                case LAPIS_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.LAPIS_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.INK_SACK, 4, 2));
                    break;
                case COAL_ORE:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.COAL_ORE, 2));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.COAL, 2));
                    break;
            }
        }
    }

}
