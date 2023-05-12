package cn.jackcraft.runforyourlife;

import cn.jackcraft.runforyourlife.handle.ConfigHandler;
import cn.jackcraft.runforyourlife.handle.LangHandler;
import cn.jackcraft.runforyourlife.helper.LoggerHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class RunForYourLife extends JavaPlugin implements Listener {
    public static RunForYourLife plugin;
    public static LoggerHelper globalLogger;
    public static ConfigHandler globalConfig;
    public static LangHandler globalLang;
    @Override
    public void onLoad(){
        // 插件加载逻辑 - 1
        saveDefaultConfig();
        plugin = this;
        // 初始化日志
        globalLogger = new LoggerHelper();
        // 初始化配置文件
        globalConfig = new ConfigHandler(this);
        // 初始化语言
        globalLang = new LangHandler(this);
    }
    @Override
    public void onEnable() {
        // 插件加载逻辑 - 2

        /* Simplified Chinese */
        final Locale locale = Locale.getDefault();
        if (locale.getLanguage().equals("zh") && locale.getCountry().equals("CN")) {
            globalLogger.Info("欢迎使用 逃往生处 插件！");
        } else /* American English */ {
            globalLogger.Info("You're using plugin \"Run for your life\", Hooray!");
        }

    }
    @Override
    public void onDisable() {
        // 插件禁用逻辑
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
/*        if (!ConfigIsInit()){
            if (player.isOp()) {

            }
        }*/
    }
}
