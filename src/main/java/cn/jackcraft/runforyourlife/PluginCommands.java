package cn.jackcraft.runforyourlife;

import cn.jackcraft.runforyourlife.executions.theory.AbstractCommand;
import cn.jackcraft.runforyourlife.executions.theory.CommandSet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommands implements CommandExecutor {

    // 这是一个定义实例
    private final CommandSet globalCommandSet = new CommandSet(
            new AbstractCommand[] {
                    new AbstractCommand("List all commands for me!", "listall") {
                        @Override
                        public int work() {
                            /* Da da da
                             * 在这里实现 "listall" 功能
                             * :) */
                            return 0; // 返回值由实际功能决定
                                      // 返回 0 为正常，其余均为异常
                                      // 如果没有需要实现，留下 return 0; 即可
                                      // *WINK* ;)
                        }
                        @Override
                        public int work(CommandSender sender, Command cmd, String label) {
                            return 0;
                        }
                    },
                    new AbstractCommand("Help me~ QwQ", "help",
                            // 若是存在子命令，请如此定义:
                            new CommandSet(
                                    new AbstractCommand[] {
                                            new AbstractCommand("Help's help", "help") {
                                                @Override
                                                public int work() {
                                                    /* 输出关于 help 的 help 文档
                                                       Bala bala */
                                                    return 0;
                                                }

                                                @Override
                                                public int work(CommandSender sender, Command cmd, String label) {
                                                    /* Bala bala */
                                                    sender.sendMessage("test");
                                                    return 0;
                                                }
                                            },
                                            new AbstractCommand("Help -> listall", "listall") {
                                                @Override
                                                public int work() {
                                                    /* 输出关于 listall 的 help 文档
                                                       :D */
                                                    return 0;
                                                }

                                                @Override
                                                public int work(CommandSender sender, Command cmd, String label) {
                                                    /* :P
                                                     * 依次类推哦~
                                                     * \(=^v^=)/
                                                     */
                                                    return 0;
                                                }
                                            }
                                    }
                            )) {
                        @Override
                        public int work() {
                            /* Da da da
                             * 在这里实现 "help" 功能
                             * :) */
                            return 0; // 返回值由实际功能决定
                                      // 返回 0 为正常，其余均为异常
                        }
                        // 如果没有需要实现，留下 return 0; 即可
                        @Override
                        public int work(CommandSender sender, Command cmd, String label) {
                            sender.sendMessage("test");
                            return 0;
                        }
                    },
                    // new AbstractCommand("...", "...") {...
            }
    );

/*    @Override
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
    }*/

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        for (String curr : args) {
            final int commandIndex = globalCommandSet.validation(curr);

            /* Have matches: call work() */
            if (commandIndex != -1) {
                // 这是一个调用实例
                // 使用 CommandSet 中的 validation 以判定对象命令为合法命令，
                // 随后即可调用已定义的抽象方法 work()
                //                      或是 work(CommandSender, org.bukkit.command.Command, String) 以运行既定功能
                // 命令功能明细请在 globalCommandSet 中实现

                // 有问题 Q 我就行~ :)
                return (globalCommandSet.getCommand(commandIndex).work(sender, cmd, label) == 0);
            } else {
                sender.sendMessage("Illegal command!");
            }

        }
        /* Command not found, ignored. */
        return true;
    }

}
