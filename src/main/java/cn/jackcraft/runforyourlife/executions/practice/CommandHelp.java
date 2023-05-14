package cn.jackcraft.runforyourlife.executions.practice;

import cn.jackcraft.runforyourlife.executions.theory.AbstractCommand;
import cn.jackcraft.runforyourlife.executions.theory.CommandSet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp extends AbstractCommand {

    public CommandHelp(String commandName, String identifyName) {
        super(commandName, identifyName);

        this.setSubcommands(
                new CommandSet(
                    this.getSubCommands(),
                    new AbstractCommand[] {
                            new AbstractCommand("", "") {
                                @Override
                                public int work() {
                                    return 0;
                                }

                                @Override
                                public int work(CommandSender sender, Command cmd, String label) {
                                    return 0;
                                }
                            }
                    }
                )
        );
    }

    public CommandHelp(String commandName, String identifyName, CommandSet subcommands) {
        super(commandName, identifyName, subcommands);
    }

    public int work() {
        // TODO:
        return 0;
    }

    public int work(CommandSender sender, org.bukkit.command.Command cmd, String label) {
        // TODO:
        return 0;
    }

}
