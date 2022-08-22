package com.wamel.dayjob.event;

import com.wamel.dayjob.DayJob;
import com.wamel.dayjob.config.ConfigManager$Player;
import com.wamel.dayjob.data.DataManager$Player;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    private static final DayJob plugin = DayJob.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if(DataManager$Player.playerData.get(uuid) != null)
            return;

        ConfigManager$Player.load(uuid);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        ConfigManager$Player.save(uuid);
    }

}
