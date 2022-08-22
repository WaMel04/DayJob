package com.wamel.dayjob;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import com.wamel.dayjob.addon.*;
import com.wamel.dayjob.command.CMD$Jobreload;
import com.wamel.dayjob.config.ConfigManager$Player;
import com.wamel.dayjob.data.DataManager$Player;
import com.wamel.dayjob.event.PlayerEvent;
import com.wamel.dayjob.job.Job$Enchanter;
import com.wamel.dayjob.job.Job$Farmer;
import com.wamel.dayjob.job.Job$Miner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class DayJob extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerCommands();
        registerEvents();
        registerJobs();
        registerAddon();

        for(Player player : Bukkit.getOnlinePlayers()) {
            ConfigManager$Player.load(player.getUniqueId().toString());
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                ConfigManager$Player.saveAll();
            }
        }.runTaskTimer(this, 20L*300, 20L*300);
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

    private void registerCommands() {
        this.getCommand("jreload").setExecutor(new CMD$Jobreload());
        this.getCommand("jobreload").setExecutor(new CMD$Jobreload());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
    }

    private void registerJobs() {
        getServer().getPluginManager().registerEvents(new Job$Farmer(), this);
        getServer().getPluginManager().registerEvents(new Job$Miner(), this);
        getServer().getPluginManager().registerEvents(new Job$Enchanter(), this);
    }

    private void registerAddon() {
        try {
            Skript.registerAddon(this);
            Skript.registerExpression(Expr$Job.class, String.class, ExpressionType.PROPERTY,
                    new String[] { "%string%'s job" });
            Skript.registerExpression(Expr$Level.class, Double.class, ExpressionType.PROPERTY,
                    new String[] { "%string%'s job level" });
            Skript.registerExpression(Expr$MaxExp.class, Double.class, ExpressionType.PROPERTY,
                    new String[] { "%string%'s job maxexp" });
            Skript.registerExpression(Expr$Exp.class, Double.class, ExpressionType.PROPERTY,
                    new String[] { "%string%'s job exp" });
            Skript.registerExpression(Expr$JobPoint.class, Double.class, ExpressionType.PROPERTY,
                    new String[] { "%string%'s job point" });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // API
    public static String getJob(String uuid) {
        return DataManager$Player.getJob(uuid);
    }

    public static void setJob(String uuid, String job) {
        DataManager$Player.setJob(uuid, job);
    }

    public static int getLevel(String uuid) {
        return DataManager$Player.getLevel(uuid);
    }

    public static void setLevel(String uuid, int level) {
        DataManager$Player.setLevel(uuid, level);
    }

    public static void addLevel(String uuid, int level) {
        DataManager$Player.addLevel(uuid, level);
    }

    public static int getMaxExp(String uuid) {
        return DataManager$Player.getMaxExp(uuid);
    }

    public static int getMaxExp(int level) {
        return DataManager$Player.getMaxExp(level);
    }

    public static void setMaxExp(String uuid, int maxExp) {
        DataManager$Player.setMaxExp(uuid, maxExp);
    }

    public static int getExp(String uuid) {
        return DataManager$Player.getExp(uuid);
    }

    public static void setExp(String uuid, int exp) {
        DataManager$Player.setExp(uuid, exp);
    }

    public static void addExp(String uuid, int exp) {
        DataManager$Player.addExp(uuid, exp);
    }

    public static int getJobPoint(String uuid) {
        return DataManager$Player.getJobPoint(uuid);
    }

    public static void setJobPoint(String uuid, int jobPoint) {
        DataManager$Player.setJobPoint(uuid, jobPoint);
    }

    public static void addJobPoint(String uuid, int jobPoint) {
        DataManager$Player.addJobPoint(uuid, jobPoint);
    }

    public static void levelUp(String uuid) {
        DataManager$Player.levelUp(uuid);
    }

}
