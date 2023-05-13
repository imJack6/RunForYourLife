package cn.jackcraft.runforyourlife.handle;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cn.jackcraft.runforyourlife.RunForYourLife.hasPlaceholderAPI;

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
        handle = getPAPIHandling(player, handle);
        // ...
        return handle;
    }

    public static String getPAPIHandling(Player player, String inputString){
        String handle = inputString;
        if (hasPlaceholderAPI())
            handle = PlaceholderAPI.setPlaceholders(player, handle);
        return handle;
    }
}
