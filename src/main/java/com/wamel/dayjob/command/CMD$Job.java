package com.wamel.dayjob.command;

import com.wamel.dayjob.gui.GUI$JobSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD$Job implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (cmd.getName()) {
            case "job":
            case "직업":
                break;
            default:
                return false;
        }

        Player player = (Player) sender;
        GUI$JobSelect.open(player);
        return false;
    }

}
