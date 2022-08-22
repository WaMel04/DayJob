package com.wamel.dayjob.data;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.config.ConfigManager$Config;

public class DataManager$Config {

    private static final DayJob plugin = DayJob.getInstance();

    public static int EXP_BONUS = (int) ConfigManager$Config.read("EXP_BONUS");
    public static int MAX_LEVEL = (int) ConfigManager$Config.read("MAX_LEVEL");

}
