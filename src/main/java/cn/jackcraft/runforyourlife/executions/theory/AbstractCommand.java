package cn.jackcraft.runforyourlife.executions.theory;

/**
 * @author William
 */
public abstract class AbstractCommand implements ICommands {
    // 纯属名称
    private final String commandName;
    // 用于String鉴别
    private final String identifyName;
    // 子命令
    private CommandSet subcommands;
    private final boolean hasSubcommand;

    private boolean beenBlackListed;

    public AbstractCommand(String commandName, String identifyName) {
        this.commandName = commandName;
        this.identifyName = identifyName;
        this.subcommands = null;
        this.hasSubcommand = false;
        this.beenBlackListed = false;
    }

    public AbstractCommand(String commandName, String identifyName, CommandSet subcommands) {
        this.commandName = commandName;
        this.identifyName = identifyName;
        this.subcommands = subcommands;
        this.hasSubcommand = true;
        this.beenBlackListed = false;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getIdentifyName() {
        return this.identifyName;
    }

    public CommandSet getSubCommands() {
        return this.subcommands;
    }

    protected void setSubcommands(CommandSet newSubcommands) {
        this.subcommands = newSubcommands;
    }

    public boolean getHasSubcommand() {
        return this.hasSubcommand;
    }

    public boolean isBeenBlackListed() {
        return beenBlackListed;
    }

    public void setBeenBlackListed(boolean beenBlackListed) {
        this.beenBlackListed = beenBlackListed;
    }

    public boolean equalsAs(AbstractCommand another) {
        return (this.getIdentifyName().equals(another.getIdentifyName())
                || this.equals(another));
    }
}
