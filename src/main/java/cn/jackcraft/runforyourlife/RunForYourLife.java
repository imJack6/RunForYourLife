package cn.jackcraft.runforyourlife;

import cn.jackcraft.runforyourlife.handle.ConfigHandler;
import cn.jackcraft.runforyourlife.handle.LangHandler;
import cn.jackcraft.runforyourlife.helper.LoggerHelper;
import cn.jackcraft.runforyourlife.literature.StringPool;
import org.bukkit.Bukkit;
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
    public static final Locale DEFAULT_SYSTEM_LOCALE = Locale.getDefault();
    public static StringPool globalStringPool = new StringPool();
    @Override
    public void onLoad(){
        // 插件加载逻辑 - 1
        saveDefaultConfig();
        plugin = this;
        // 初始化配置文件
        globalConfig = new ConfigHandler(this);
        // 初始化日志记录器
        globalLogger = new LoggerHelper();
        // 初始化语言
        globalLogger.debug("Initialize local language...");
        globalLang = new LangHandler(this);

        globalLogger.debug("The Load phase ends");
    }
    @Override
    public void onEnable() {
        // 插件加载逻辑 - 2
        globalLogger.debug("Start the Enable phase...");
        globalLogger.debug("Registering for events...");
        // 注册事件
        Bukkit.getPluginManager().registerEvents(this,this);
        globalLogger.debug("Registering for commands...");
        // 注册指令
        getCommand("RunForYourLife").setExecutor(new PluginCommands());
        globalLogger.debug("Detecting the presence of the PlaceholderAPI...");
        if (!hasPlaceholderAPI()) {
            globalLogger.info(globalStringPool.getFromPool(StringPool.TextSelections.placeholderAPINotFound));
        }
        globalLogger.info(globalStringPool.getFromPool(StringPool.TextSelections.welcome));
        globalLogger.debug("Detects if the battlefield or profile has been initialized...");
        if (!globalConfig.getGameInit()) {
            globalLogger.info(globalLang.getLang("SYSTEM.NOT_INIT"));
        }
        globalLogger.debug("The Enable phase ends");
    }
    @Override
    public void onDisable() {
        // 插件禁用逻辑
        globalLogger.debug("Start the Disable phase...");
        globalLogger.info(globalStringPool.getFromPool(StringPool.TextSelections.bye));
        globalLogger.debug("The Disable phase ends");
    }
    public static boolean hasPlaceholderAPI(){
        return (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        globalLogger.debug("Player " + p.getName() + " joins. op=" + p.isOp() + " Player=" + p);
        if (!globalConfig.getGameInit()){
            if (p.isOp()) {
                globalLogger.playerMsg(p, globalLang.getLang("SYSTEM.NOT_INIT"));
            }
        }
    }
}
