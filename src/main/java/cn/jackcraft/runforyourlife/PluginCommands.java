package cn.jackcraft.runforyourlife;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cn.jackcraft.runforyourlife.RunForYourLife.globalLogger;
import static cn.jackcraft.runforyourlife.RunForYourLife.globalLang;

public class PluginCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        Player p = null;
        if (sender instanceof Player)
            p = (Player) sender;
        // p == null 为控制台。sender为指令发送者，有可能是玩家，也可能是控制台。

        // 根指令
        if (args.length == 0) {
            String stringLang = globalLang.getLang("COMMAND.UNKNOWN_COMMAND");
            stringLang = stringLang.replace("{RootCommand}", label);
            globalLogger.commandSenderMsg(false, sender, stringLang);
        }
        // 根指令 help {int}
        if (args.length >= 1 && args[0].equals("help")) {
            if (args[0].equals("help")) {
                if (p == null || p.hasPermission("runforyourlife.help")) {
                    String LangNode = "COMMAND.HELP_PAGE_1";
                    if (args.length == 2)
                        LangNode = "COMMAND.HELP_PAGE_" + args[1];
                    if (globalLang.hasLangNode(LangNode)){
                        String stringLang = globalLang.getLang(LangNode);
                        stringLang = stringLang.replace("{TotalHelpPage}","2");
                        stringLang = stringLang.replace("{RootCommand}", label);
                        globalLogger.commandSenderMsg(false, sender, stringLang);
                    } else {
                        String stringLang = globalLang.getLang("COMMAND.UNKNOWN_COMMAND");
                        stringLang = stringLang.replace("{RootCommand}", label);
                        globalLogger.commandSenderMsg(false, sender, stringLang);
                    }
                }
            }
        }
        return true;
    }


/*    public static class AbstractCommand {
        // 纯属名称
        private final String commandName;
        // 用于String鉴别
        private final String displayName;
        // 子命令
        private final AbstractCommand subcommand;
        private final boolean hasSubcommand;

        public AbstractCommand(String commandName, String displayName) {
            this.commandName = commandName;
            this.displayName = displayName;
            this.subcommand = null;
            this.hasSubcommand = false;
        }

        public AbstractCommand(String commandName, String displayName, AbstractCommand subcommand) {
            this.commandName = commandName;
            this.displayName = displayName;
            this.subcommand = subcommand;
            this.hasSubcommand = true;
        }

        public String getCommandName() {
            return this.commandName;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public AbstractCommand getSubCommand() {
            return this.subcommand;
        }

        public boolean getHasSubcommand() {
            return this.hasSubcommand;
        }
    }

    public static class CommandLine {
        private final AbstractCommand[] commands;
        private final boolean isComment;

        
    }


}
