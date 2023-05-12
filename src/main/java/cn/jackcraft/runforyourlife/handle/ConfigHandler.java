package cn.jackcraft.runforyourlife.handle;

import cn.jackcraft.runforyourlife.RunForYourLife;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {
    private String pluginPrefix = "&7[&6RunForYourLife&7]";
    /* Set up fall back language -> Simplified Chinese */
    private String pluginLang = "cn";
    private RunForYourLife plugin;
    public ConfigHandler(RunForYourLife plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        loadDefaultConfig();
    }
    public void loadDefaultConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        pluginPrefix = config.getString("plugin-config.prefix");
        pluginLang = config.getString("plugin-config.lang");
    }
    public String getPluginPrefix() {
        return pluginPrefix;
    }
    public String getPluginLang() {
        return pluginLang;
    }
}
