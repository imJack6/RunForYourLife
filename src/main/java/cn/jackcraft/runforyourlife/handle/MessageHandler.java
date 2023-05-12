package cn.jackcraft.runforyourlife.handle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageHandler {
    public static String getConsoleMessageHandling(String inputString) {
        String handle = inputString;
        handle = ChatColor.translateAlternateColorCodes('&', handle);
        // ...
        return handle;
    }

    public static String getPlayerMessageHandling(Player player, String inputString) {
        String handle = inputString;
        handle = ChatColor.translateAlternateColorCodes('&', handle);
        // ...
        return handle;
    }
}
