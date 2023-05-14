package cn.jackcraft.runforyourlife;

import cn.jackcraft.runforyourlife.handle.ConfigHandler;
import cn.jackcraft.runforyourlife.handle.LangHandler;
import cn.jackcraft.runforyourlife.helper.LoggerHelper;
import cn.jackcraft.runforyourlife.literature.StringPool;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
    public void onLoad() {
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
        //openInventory(p);
    }

/*    public void openInventory(HumanEntity player) {
        // 创建一个包含9个物品槽的背包
        Inventory inventory = Bukkit.createInventory(null, 9, "My Inventory");

        // 在背包中添加物品
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        inventory.setItem(0, item);

        // 打开GUI菜单
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("My Inventory")) {
            return;
        }

        // 防止玩家拖动物品到其他位置
        event.setCancelled(true);

        // 处理物品点击事件
        if (event.getRawSlot() == 0) {
            event.getWhoClicked().sendMessage("You clicked on a diamond");
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("My Inventory")) {
            // 处理玩家关闭GUI菜单事件
        }
    }
    public void ChestTest(){
        // 创建一个包含9个物品槽的背包
        Inventory inventory = Bukkit.createInventory(null, 9, "My Inventory");

        // 在背包中添加物品
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        inventory.setItem(0, item);

        // 打开GUI菜单
        //player.openInventory(inventory);
    }*/
}
