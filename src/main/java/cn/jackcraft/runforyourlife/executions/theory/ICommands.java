package cn.jackcraft.runforyourlife.executions.theory;

import org.bukkit.command.CommandSender;

/**
 * @author William
 */
public interface ICommands {

    // Returns return code from the command
    int work();

    // Returns return code from the command
    int work(CommandSender sender, org.bukkit.command.Command cmd, String label);

}
