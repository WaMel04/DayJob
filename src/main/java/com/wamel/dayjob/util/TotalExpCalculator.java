package com.wamel.dayjob.util;

public class TotalExpCalculator {

    public static int getTotalExp(int level) {
        if(level <= 16)
            return (level*level + 6*level);
        else if(level <= 31)
            return (int) (2.5*level*level - 40.5*level + 360);
        return (int) (4.5*level*level - 162.5*level + 2220);
    }

}
