package cn.jackcraft.runforyourlife.helper;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.jackcraft.runforyourlife.handle.ConfigHandler;

import static cn.jackcraft.runforyourlife.RunForYourLife.globalConfig;
import static cn.jackcraft.runforyourlife.handle.MessageHandler.getConsoleMessageHandling;
import static cn.jackcraft.runforyourlife.handle.MessageHandler.getPlayerMessageHandling;

public class LoggerHelper {
    private final CommandSender GLOBAL_COMMAND_SENDER;

    public LoggerHelper()
    {
        this.GLOBAL_COMMAND_SENDER = Bukkit.getConsoleSender();
    }
    public void Info(String Msg) {
        Info(true, Msg);
    }
    public void Info(boolean hasPrefix, String msg) {
        GLOBAL_COMMAND_SENDER.sendMessage((hasPrefix)
                ? getConsoleMessageHandling(globalConfig.getPluginPrefix() + " " + msg)
                : getConsoleMessageHandling(msg));
    }

    public void PlayerMsg(Player player, String msg) {
        PlayerMsg(true, player, msg);
    }
    public void PlayerMsg(boolean hasPrefix, Player player, String msg) {
        player.sendMessage((hasPrefix)
                ? getPlayerMessageHandling(player, globalConfig.getPluginPrefix() + " " + msg)
                : getPlayerMessageHandling(player, msg));
    }
}
