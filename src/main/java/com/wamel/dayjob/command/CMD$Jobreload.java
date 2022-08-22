package com.wamel.dayjob.command;

import com.wamel.dayjob.config.ConfigManager$Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD$Jobreload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (cmd.getName()) {
            case "jreload":
            case "jobreload":
                break;
            default:
                return false;
        }

        if(!(sender.isOp())) {
            sender.sendMessage(" §c§l● §f권한이 부족합니다.");
            return false;
        }

        ConfigManager$Config.reload();
        sender.sendMessage(" §6§l▶ §fconfig를 리로드했습니다.");
        return false;
    }

}
