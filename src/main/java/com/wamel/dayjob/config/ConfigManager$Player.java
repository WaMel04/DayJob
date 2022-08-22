package com.wamel.dayjob.config;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Player;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ConfigManager$Player {

    private static final DayJob plugin = DayJob.getInstance();

    public static void load(String uuid) {
        File file = new File(plugin.getDataFolder() + "//player//" + uuid + ".yml");

        if(!(file.exists())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("job", "none");
            map.put("level", 1);
            map.put("exp", 0);
            map.put("jobpoint", 0);
            DataManager$Player.playerData.put(uuid, map);
            return;
        }

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        HashMap<String, Object> map = new HashMap<>();
        map.put("job", yaml.getString("job"));
        map.put("level", yaml.getInt("level"));
        map.put("exp", yaml.getInt("exp"));
        map.put("jobpoint", yaml.getInt("jobpoint"));
        DataManager$Player.playerData.put(uuid, map);
    }

    public static void save(String uuid) {
        File file = new File(plugin.getDataFolder() + "//player//" + uuid + ".yml");

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        HashMap<String, Object> map = DataManager$Player.playerData.get(uuid);

        yaml.set("job", map.get("job"));
        yaml.set("level", map.get("level"));
        yaml.set("exp", map.get("exp"));
        yaml.set("jobpoint", map.get("jobpoint"));

        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataManager$Player.playerData.remove(uuid);
    }

    public static void saveAll() {
        Iterator itr = DataManager$Player.playerData.keySet().iterator();

        while(itr.hasNext()) {
            String uuid = (String) itr.next();
            File file = new File(plugin.getDataFolder() + "//player//" + uuid + ".yml");

            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            HashMap<String, Object> map = DataManager$Player.playerData.get(uuid);

            yaml.set("job", map.get("job"));
            yaml.set("level", map.get("level"));
            yaml.set("exp", map.get("exp"));
            yaml.set("jobpoint", map.get("jobpoint"));

            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
