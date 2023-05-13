package cn.jackcraft.runforyourlife.handle;

import cn.jackcraft.runforyourlife.RunForYourLife;
import cn.jackcraft.runforyourlife.literature.StringPool;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static cn.jackcraft.runforyourlife.RunForYourLife.*;

public class ConfigHandler {
    private String pluginPrefix = "&7[&6RunForYourLife&7]";
    private String pluginLang = "cn";
    private int pluginConfigVersion = 1;
    private boolean pluginDebugMode = false;
    private boolean gameInit = false;
    private RunForYourLife plugin;
    public ConfigHandler(RunForYourLife plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        loadDefaultConfig();
    }
    public void loadDefaultConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (pluginConfigVersion != config.getInt("version")) {
            Bukkit.getLogger().warning(globalStringPool.getFromPool(StringPool.TextSelections.versionNotMatched));
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        pluginPrefix = config.getString("plugin-config.prefix");
        pluginLang = config.getString("plugin-config.lang");
        pluginDebugMode = config.getBoolean("plugin-config.debug");
        gameInit = config.getBoolean("game-config.init");
    }
    public String getPluginPrefix() {
        return this.pluginPrefix;
    }
    public String getPluginLang() {
        return this.pluginLang;
    }
    public boolean getGameInit() {
        return this.gameInit;
    }
    public boolean getPluginDebugMode() {
        return this.pluginDebugMode;
    }
}
