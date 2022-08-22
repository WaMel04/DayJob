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

public class Job$Farmer implements Listener {

    public static int test = 1;
    private static final DayJob plugin = DayJob.getInstance();

    private static final String JOB_CODE = "Farmer";

    private static final int JOB_EXP = 35;
    private static final int JOB_DROP_CHANCE = 20;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.isCancelled())
            return;

        switch(event.getBlock().getType()) {
            case PUMPKIN:
            case MELON_BLOCK:
            case CACTUS:
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

        switch(event.getBlock().getType()) {
            case POTATO:
            case CARROT:
            case CROPS:
                if(event.getBlock().getData() != 7)
                    return;
                break;
            case COCOA:
                if(event.getBlock().getData() < 8)
                    return;
                break;
            case BEETROOT_BLOCK:
                if(event.getBlock().getData() != 3)
                    return;
                break;
            case PUMPKIN:
            case MELON_BLOCK:
            case CACTUS:
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

        DataManager$Player.addExp(player, JOB_EXP * DataManager$Config.EXP_BONUS);

        Random random = new Random();
        if(random.nextInt(100) < JOB_DROP_CHANCE) {
            switch(event.getBlock().getType()) {
                case POTATO:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.POTATO_ITEM, 1));
                    break;
                case PUMPKIN:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.PUMPKIN, 1));
                    break;
                case MELON_BLOCK:
                    if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.MELON_BLOCK, 1));
                    else
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.MELON, 1));
                    break;
                case CARROT:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.CARROT_ITEM, 1));
                    break;
                case CROPS:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.WHEAT, 1));
                    break;
                case CACTUS:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.CACTUS, 1));
                    break;
                case COCOA:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.INK_SACK, 3, 1));
                    break;
                case BEETROOT_BLOCK:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.BEETROOT, 1));
                    break;
            }
        }
    }

}
