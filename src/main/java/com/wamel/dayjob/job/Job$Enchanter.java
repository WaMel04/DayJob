package com.wamel.dayjob.job;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Config;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.util.TotalExpCalculator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Job$Enchanter implements Listener {

    private static final DayJob plugin = DayJob.getInstance();

    private static final String JOB_CODE = "Enchanter";

    private static final int JOB_EXP_MULTIPLE = 10;
    private static final Double JOB_MCEXP_MULTIPLE = 1.5;
    private static final Double JOB_COST_REDUCE_MULTIPLE = 0.75;

    // 이벤트
    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        Player player = event.getEnchanter();
        String uuid = player.getUniqueId().toString();

        int exp = TotalExpCalculator.getTotalExp(event.getExpLevelCost()) * JOB_EXP_MULTIPLE * DataManager$Config.EXP_BONUS;
        DataManager$Player.addExp(player, exp);

        int reduceLevel = (int) (event.getExpLevelCost() * JOB_COST_REDUCE_MULTIPLE);
        int button = event.whichButton() + 1;

        new BukkitRunnable() {
            @Override
            public void run() {
                if(!(DataManager$Player.getJob(uuid).equalsIgnoreCase(JOB_CODE))) {
                    player.setLevel(player.getLevel() + button - event.getExpLevelCost());
                    return;
                }
                player.setLevel(player.getLevel() + button - reduceLevel);
            }
        }.runTaskLater(plugin, 1L);
    }

    @EventHandler
    public void onAnvilUse(InventoryClickEvent event) {
        if(event.isCancelled())
            return;
        if(!(event.getInventory() instanceof AnvilInventory) || event.getRawSlot() != 2 ||
                event.getAction().equals(InventoryAction.NOTHING) || event.getAction().equals(InventoryAction.UNKNOWN) ||
                event.getAction().equals(InventoryAction.PLACE_ALL) || event.getAction().equals(InventoryAction.PLACE_ONE) ||
                event.getAction().equals(InventoryAction.PLACE_SOME))
            return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        if(!(DataManager$Player.getJob(uuid).equalsIgnoreCase(JOB_CODE)))
            return;

        AnvilInventory anvil = (AnvilInventory) event.getInventory();

        int exp = TotalExpCalculator.getTotalExp(anvil.getRepairCost()) * JOB_EXP_MULTIPLE * DataManager$Config.EXP_BONUS;
        DataManager$Player.addExp(player, exp);

        anvil.setRepairCost((int) Math.ceil(anvil.getRepairCost() * JOB_COST_REDUCE_MULTIPLE));
    }

    @EventHandler
    public void onExpGet(PlayerPickupExperienceEvent event) {
        if(event.isCancelled())
            return;

        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if(!(DataManager$Player.getJob(uuid).equalsIgnoreCase(JOB_CODE)))
            return;

        int exp = event.getExperienceOrb().getExperience() * 10;
        DataManager$Player.addExp(player, exp);

        event.getExperienceOrb().setExperience((int) Math.ceil(event.getExperienceOrb().getExperience() * JOB_MCEXP_MULTIPLE * DataManager$Config.EXP_BONUS));
    }
}
