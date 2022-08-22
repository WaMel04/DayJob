package com.wamel.dayjob.data;


import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.config.ConfigManager$Player;
import com.wamel.dayjob.event.CustomLevelUpEvent;
import com.wamel.dayjob.util.ActionbarSender;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DataManager$Player {

    private static final DayJob plugin = DayJob.getInstance();
    public static HashMap<String, HashMap<String, Object>> playerData = new HashMap<>();
    
    public static final int MAX_EXP_1_50 = 2000;
    public static final int MAX_EXP_51_100 = 5000;
    public static final int MAX_EXP_101_150 = 8000;
    public static final int MAX_EXP_151_200 = 10000;
    public static final int MAX_EXP_201_300 = 15000;
    public static final int MAX_EXP_301_350 = 20000;
    public static final int MAX_EXP_351_400 = 25000;
    public static final int MAX_EXP_401_500 = 35000;


    public static String getJob(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        return (String) playerData.get(uuid).get("job");
    }

    public static void setJob(String uuid, String job) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("job", job);
    }

    public static int getLevel(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        return (int) playerData.get(uuid).get("level");
    }

    public static void setLevel(String uuid, int level) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("level", level);
    }

    public static void addLevel(String uuid, int level) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("level", getLevel(uuid) + level);
    }

    public static int getMaxExp(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        int level = getLevel(uuid);
        if(level <= 50) {
            return MAX_EXP_1_50;
        } else if(level <= 100) {
            return MAX_EXP_51_100;
        } else if(level <= 150) {
            return MAX_EXP_101_150;
        } else if(level <= 200) {
            return MAX_EXP_151_200;
        } else if(level <= 300) {
            return MAX_EXP_201_300;
        } else if(level <= 350) {
            return MAX_EXP_301_350;
        } else if(level <= 400) {
            return MAX_EXP_351_400;
        } else if(level <= 500){
            return MAX_EXP_401_500;
        }
        plugin.getLogger().warning("오류: " + uuid + "의 레벨이 정상 범위에서 벗어남.");
        return 99999999;
    }

    public static int getMaxExp(int level) {
        if(level <= 50) {
            return MAX_EXP_1_50;
        } else if(level <= 100) {
            return MAX_EXP_51_100;
        } else if(level <= 150) {
            return MAX_EXP_101_150;
        } else if(level <= 200) {
            return MAX_EXP_151_200;
        } else if(level <= 300) {
            return MAX_EXP_201_300;
        } else if(level <= 350) {
            return MAX_EXP_301_350;
        } else if(level <= 400) {
            return MAX_EXP_351_400;
        } else if(level <= 500) {
            return MAX_EXP_401_500;
        }
        plugin.getLogger().warning("오류: 정상 범위에서 벗어난 레벨의 최대 경험치 요청.");
        return 99999999;
    }

    public static void setMaxExp(String uuid, int maxExp) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("maxExp", maxExp);
    }

    public static int getExp(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        return (int) playerData.get(uuid).get("exp");
    }

    public static void setExp(String uuid, int exp) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("exp", exp);
    }

    public static void addExp(String uuid, int exp) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        int currExp = getExp(uuid);
        int addExp = exp;

        playerData.get(uuid).put("exp", currExp + addExp);

        if((getExp(uuid) >= getMaxExp(uuid)) && (getLevel(uuid) != DataManager$Config.MAX_LEVEL))
            levelUp(uuid);
    }

    public static void addExp(Player player, int exp) {
        String uuid = player.getUniqueId().toString();
        int currExp = getExp(uuid);
        int addExp = exp;

        playerData.get(uuid).put("exp", currExp + addExp);

        ActionbarSender.send(player, "§6§l▶ §f+" + addExp + " 경험치 §7(" + getExp(uuid) + "/" + getMaxExp(uuid) + ")");

        if((getExp(uuid) >= getMaxExp(uuid)) && (getLevel(uuid) != DataManager$Config.MAX_LEVEL))
            levelUp(player);
    }

    public static int getJobPoint(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        return (int) playerData.get(uuid).get("jobpoint");
    }

    public static void setJobPoint(String uuid, int jobPoint) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("jobpoint", jobPoint);
    }

    public static void addJobPoint(String uuid, int jobPoint) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        playerData.get(uuid).put("jobpoint", getJobPoint(uuid) + jobPoint);
    }

    public static void levelUp(String uuid) {
        if(playerData.get(uuid) == null)
            ConfigManager$Player.load(uuid);

        while(Math.floorDiv(getExp(uuid), getMaxExp(uuid)) >= 1) {
            setExp(uuid, getExp(uuid) - getMaxExp(uuid));
            addLevel(uuid, 1);

            CustomLevelUpEvent event = new CustomLevelUpEvent(uuid, getLevel(uuid));
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    public static void levelUp(Player player) {
        String uuid = player.getUniqueId().toString();
        int level = getLevel(uuid);
        int i = 0;

        while(Math.floorDiv(getExp(uuid), getMaxExp(uuid)) >= 1) {
            setExp(uuid, getExp(uuid) - getMaxExp(uuid));
            addLevel(uuid, 1);

            CustomLevelUpEvent event = new CustomLevelUpEvent(uuid, getLevel(uuid));
            Bukkit.getServer().getPluginManager().callEvent(event);

            i++;
        }

        player.sendTitle("§6§lLEVEL UP", "§f" + level + " 레벨 -> " + (level + i) + " 레벨");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5.0f, 1.0f);
    }
}
