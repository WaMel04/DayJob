package com.wamel.dayjob.gui;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.util.ItemGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GUI$JobSelect implements Listener {

    private DayJob plugin = DayJob.getInstance();
    private static final String GUI_NAME = "§0직업 선택창";
    private static final Inventory GUI = Bukkit.createInventory(null, 9*5, GUI_NAME);
    private static List jobList = new ArrayList();

    public GUI$JobSelect() {
        for(int i=0; i<9; i++) {
            GUI.setItem(i, ItemGenerator.create(Material.STAINED_GLASS_PANE, "§f", "", 1));
            GUI.setItem(i+36, ItemGenerator.create(Material.STAINED_GLASS_PANE, "§f", "", 1));
        }
        for(int i=1; i<=3; i++) {
            GUI.setItem(i*9, ItemGenerator.create(Material.STAINED_GLASS_PANE, "§f", "", 1));
            GUI.setItem(i*9 + 8, ItemGenerator.create(Material.STAINED_GLASS_PANE, "§f", "", 1));
        }
    }

    public static void addJob(String code, Material material, String name, String description) {
        jobList.add(code);
        GUI.setItem(jobList.size()+9, ItemGenerator.create(material, name, description, 1));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getInventory().equals(GUI)))
            return;

        event.setCancelled(true);

        if(DataManager$Player.getJob(event.getWhoClicked().getUniqueId().toString()) != null) {
            event.getWhoClicked().sendMessage("§6§l직업 §7| §f당신은 이미 직업을 가지고 있습니다.");
            event.getWhoClicked().closeInventory();
            return;
        }

        // 직업 클릭시 해당 직업이 선택되는 기능 넣기..........
    }

    public static void open(Player player) {
        player.openInventory(GUI);
    }

}
