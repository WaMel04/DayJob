package com.wamel.dayjob.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomLevelUpEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private static String uuid;
    private static int level;

    public CustomLevelUpEvent(String uuid, int level) {
        this.uuid = uuid;
        this.level = level;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static String getUuid() {
        return uuid;
    }

    public static int getLevel() {
        return level;
    }

}
