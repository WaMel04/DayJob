package com.wamel.dayjob.config;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.data.DataManager$Config;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager$Config {

    private static final DayJob plugin = DayJob.getInstance();

    public static Object read(String key) {
        File file = new File(plugin.getDataFolder() + "//config.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml.get(key);
    }

    public static void reload() {
        DataManager$Config.EXP_BONUS = (int) read("EXP_BONUS");
        DataManager$Config.MAX_LEVEL = (int) read("MAX_LEVEL");
    }

}
