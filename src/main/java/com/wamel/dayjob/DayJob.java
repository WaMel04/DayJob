package com.wamel.dayjob;

import com.wamel.dayjob.command.CMD$Job;
import com.wamel.dayjob.config.ConfigManager$Player;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.event.PlayerEvent;
import com.wamel.dayjob.gui.GUI$JobSelect;
import com.wamel.dayjob.job.Job$Farmer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DayJob extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        registerJobs();

        for(Player player : Bukkit.getOnlinePlayers()) {
            ConfigManager$Player.load(player.getUniqueId().toString());
        }
    }

    @Override
    public void onDisable() {
        ConfigManager$Player.saveAll();
    }

    public static DayJob getInstance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("DayJob");
        if (plugin == null)
            return null;
        if (!(plugin instanceof DayJob))
            return null;
        return (DayJob) plugin;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new GUI$JobSelect(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
    }

    private void registerCommands() {
        this.getCommand("job").setExecutor(new CMD$Job());
        this.getCommand("직업").setExecutor(new CMD$Job());
    }

    private void registerJobs() {
        getServer().getPluginManager().registerEvents(new Job$Farmer(), this);
        //getServer().getPluginManager().registerEvents(new Job$Miner(), this);
    }

    public static String getJob(Player player) {
        return DataManager$Player.getJob(player.getUniqueId().toString());
    }
}
