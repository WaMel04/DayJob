package com.wamel.dayjob.data;

import com.wamel.dayjob.DayJob;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class DataManager$Player {

    private static DayJob plugin = DayJob.getInstance();
    public static HashMap<String, HashMap<String, Object>> playerData = new HashMap<>();

    private static int MAX_EXP_1_50 = 2000;
    private static int MAX_EXP_51_100 = 5000;
    private static int MAX_EXP_101_150 = 8000;
    private static int MAX_EXP_151_200 = 10000;
    private static int MAX_EXP_201_300 = 15000;
    private static int MAX_EXP_301_350 = 20000;
    private static int MAX_EXP_351_400 = 25000;
    private static int MAX_EXP_401_500 = 35000;

    public static String getJob(String uuid) {
        return (String) playerData.get(uuid).get("job");
    }

    public static void setJob(String uuid, String job) {
        playerData.get(uuid).put("job", job);
    }

    public static int getLevel(String uuid) {
        return (int) playerData.get(uuid).get("level");
    }

    public static void setLevel(String uuid, int level) {
        playerData.get(uuid).put("level", level);
    }

    public static void addLevel(String uuid, int level) {
        playerData.get(uuid).put("level", getLevel(uuid) + level);
    }

    public static int getMaxExp(String uuid) {
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
        playerData.get(uuid).put("maxExp", maxExp);
    }

    public static int getExp(String uuid) {
        return (int) playerData.get(uuid).get("exp");
    }

    public static void setExp(String uuid, int exp) {
        playerData.get(uuid).put("exp", exp);
    }

    public static void addExp(String uuid, int exp) {
        playerData.get(uuid).put("exp", getExp(uuid) + exp);
        final int maxLevel = 300; // 임시 상한 레벨

        if((getExp(uuid) + exp) >= getMaxExp(uuid) && getLevel(uuid) != maxLevel)
            levelUp(uuid);
    }

    public static int getJobPoint(String uuid) {
        return (int) playerData.get(uuid).get("jobpoint");
    }

    public static void setJobPoint(String uuid, int JobPoint) {
        playerData.get(uuid).put("jobpoint", JobPoint);
    }

    public static void addJobPoint(String uuid, int JobPoint) {
        playerData.get(uuid).put("jobpoint", getJobPoint(uuid) + JobPoint);
    }

    public static void levelUp(String uuid) {
        while(Math.floorDiv(getExp(uuid), getMaxExp(uuid)) >= 1) {
            setExp(uuid, getExp(uuid) - getMaxExp(uuid));
            addLevel(uuid, 1);
            Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage("레벨 업!");
        }
    }
}
