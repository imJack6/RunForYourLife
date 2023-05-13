package cn.jackcraft.runforyourlife.helper;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static cn.jackcraft.runforyourlife.RunForYourLife.globalConfig;
import static cn.jackcraft.runforyourlife.handle.MessageHandler.getConsoleMessageHandling;
import static cn.jackcraft.runforyourlife.handle.MessageHandler.getPlayerMessageHandling;

public class LoggerHelper {
    private final CommandSender GLOBAL_COMMAND_SENDER;
    private final Logger GLOBAL_BUKKIT_LOGGER;

    public LoggerHelper()
    {
        this.GLOBAL_COMMAND_SENDER = Bukkit.getConsoleSender();
        this.GLOBAL_BUKKIT_LOGGER = Bukkit.getLogger();
    }
    public void Info(String Msg) {
        Info(true, Msg);
    }
    public void Info(boolean hasPrefix, String msg) {
        GLOBAL_COMMAND_SENDER.sendMessage((hasPrefix)
                ? getConsoleMessageHandling(globalConfig.getPluginPrefix() + " " + msg)
                : getConsoleMessageHandling(msg));
    }
    public void warn(String msg) {
        warn(true, msg);
    }
    public void warn(boolean hasPrefix, String msg) {
        GLOBAL_BUKKIT_LOGGER.warning(hasPrefix ? ("[RunForYourLife] " + msg) : msg);
    }

    public void debug(String msg){
        if (globalConfig.getPluginDebugMode())
            GLOBAL_COMMAND_SENDER.sendMessage(getConsoleMessageHandling("&7[&6RunForYourLife&7] &c[Debug] &7" + msg));
    }

    public void playerMsg(Player player, String msg) {
        playerMsg(true, player, msg);
    }
    public void PlayerMsg(boolean hasPrefix, Player player, String msg) {
        player.sendMessage((hasPrefix)
                ? getPlayerMessageHandling(player, globalConfig.getPluginPrefix() + " " + msg)
                : getPlayerMessageHandling(player, msg));
    }

    public void commandSenderMsg(CommandSender sender, String msg) {
        commandSenderMsg(true, sender, msg);
    }
    public void commandSenderMsg(boolean hasPrefix, CommandSender sender, String msg) {
        if (sender instanceof Player){
            Player p = ((Player) sender).getPlayer();
            sender.sendMessage((hasPrefix)
                    ? getPlayerMessageHandling(p, globalConfig.getPluginPrefix() + " " + msg)
                    : getPlayerMessageHandling(p, msg));
        } else {
            sender.sendMessage((hasPrefix)
                    ? getConsoleMessageHandling(globalConfig.getPluginPrefix() + " " + msg)
                    : getConsoleMessageHandling(msg));
        }
    }
}
