package cn.jackcraft.runforyourlife.handle;

import cn.jackcraft.runforyourlife.RunForYourLife;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.jackcraft.runforyourlife.RunForYourLife.globalConfig;
import static cn.jackcraft.runforyourlife.RunForYourLife.globalLogger;

public class LangHandler {
    private RunForYourLife plugin;
    private File langFile;
    private FileConfiguration lang;
    public LangHandler(RunForYourLife plugin) {
        this.plugin = plugin;
        plugin.saveResource("messages/message_cn.yml", false);
        plugin.saveResource("messages/message_en.yml", false);
        loadLang();
    }
    public void loadLang() {
        File folder = new File(plugin.getDataFolder().getPath() + "/messages");
        File[] filesInFolder = folder.listFiles();
        ArrayList<File> foundMessageFiles = new ArrayList<>(0);
        /* Search for files with named in pattern "message_*.yml" */
        final Pattern p = Pattern.compile("message_[a-zA-Z_]+\\.yml");
        if (filesInFolder != null) {
            for (File file : filesInFolder) {
                if (file.isFile() && p.matcher(file.getName()).matches()) {
                    foundMessageFiles.add(file);
                }
            }
        } else {
            throw new NullPointerException("Cannot get files from folder " + folder.getAbsolutePath() + "\n");
        }
        langFile = new File(plugin.getDataFolder(), "messages/message_cn.yml");       // 初始化默认中文语言
        lang = YamlConfiguration.loadConfiguration(langFile);
        for (File file : foundMessageFiles) {
            final String configFileName = ("message_" + globalConfig.getPluginLang() + ".yml");
            if (configFileName.equals(file.getName())) {
                lang = YamlConfiguration.loadConfiguration(file);   // 找到目标语言并设置为目标语言
            }
        }
    }
    public String getLang(String langNode) {
        if (lang.contains(langNode))
            return lang.getString(langNode);
        else
            return langNode;
    }
    public boolean hasLangNode(String langNode){
        return lang.contains(langNode);
    }
}
