package cn.jackcraft.runforyourlife.executions.theory;

/**
 * @author William
 * 该类将适用于 Console 读取
 */
public abstract class AbstractCommandLine {
    private final AbstractCommand[] commands;
    private final boolean isComment;

    public AbstractCommandLine(AbstractCommand[] commands) {
        this.commands = commands;
        this.isComment = false;
    }

    public AbstractCommandLine(AbstractCommand[] commands, boolean isComment) {
        this.commands = commands;
        this.isComment = isComment;
    }

    // Returns return code from whole line
    //         -1 once failed
    protected abstract int execute(AbstractCommandLine cmdLine);

    // Fails once indexToStartFrom is out of range
    // Returns -1 once failed
    protected abstract int execute(AbstractCommandLine cmdLine, int indexToStartFrom);

    public boolean getIsComment() {
        return this.isComment;
    }

    public AbstractCommand[] getCommands() {
        return this.commands;
    }
}

