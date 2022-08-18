package com.wamel.dayjob.job;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.gui.GUI$JobSelect;
import com.wamel.dayjob.util.ItemGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class Job$Farmer implements Listener {

    private static DayJob plugin = DayJob.getInstance();

    private static final String JOB_CODE = "Farmer";

    // GUI
    private static final String JOB_DISPLAY_NAME = "§6농부";
    private static final Material JOB_MATERIAL = Material.WHEAT;
    private static final String JOB_DESCRIPTION = "§f|| §6● §f작물 수급을 쉽고 안정적으로 할 수 있습니다.||§f" +
            "|| §e● §f우클릭시 직업을 선택합니다.||§f";

    private static final int JOB_EXP = 35000;
    private static final int JOB_DROP_CHANCE = 20;

    public Job$Farmer() {
        GUI$JobSelect.addJob(JOB_CODE, JOB_MATERIAL, JOB_DISPLAY_NAME, JOB_DESCRIPTION);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        switch(event.getBlock().getType()) {
            case POTATO:
            case PUMPKIN:
            case MELON_BLOCK:
            case CARROT:
            case CROPS:
            case CACTUS:
            case COCOA:
            case BEETROOT_BLOCK:
            case NETHER_WARTS:
                break;
            default:
                return;
        }

        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if(!(DataManager$Player.getJob(uuid).equalsIgnoreCase(JOB_CODE)))
            return;

        DataManager$Player.addExp(uuid, JOB_EXP);
        Location loc = event.getBlock().getLocation();

        Random random = new Random();
        if(random.nextInt(100) < JOB_DROP_CHANCE) {
            switch(event.getBlock().getType()) {
                case POTATO:
                    if(event.getBlock().getData() == 7)
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
                    if(event.getBlock().getData() == 7)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.CARROT_ITEM, 1));
                    break;
                case CROPS:
                    if(event.getBlock().getData() == 7)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.WHEAT, 1));
                    break;
                case CACTUS:
                    loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.CACTUS, 1));
                    break;
                case COCOA:
                    if(event.getBlock().getData() == 2)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.INK_SACK, 3, 1));
                    break;
                case BEETROOT_BLOCK:
                    if(event.getBlock().getData() == 3)
                        loc.getWorld().dropItemNaturally(loc, ItemGenerator.create(Material.BEETROOT, 1));
                    break;
            }
        }
    }

}
