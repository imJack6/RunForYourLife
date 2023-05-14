package cn.jackcraft.runforyourlife.executions.theory;

/**
 * @author William
 * 该类将适用于 Console 读取
 */
public class CommandLine extends AbstractCommandLine {
    public CommandLine(AbstractCommand[] commands) {
        super(commands);
    }

    public CommandLine(AbstractCommand[] commands, boolean isComment) {
        super(commands, isComment);
    }

    // Returns return code from whole line
    //         -1 once failed
    protected int execute(AbstractCommandLine cmdLine) {
        /* Arguments check */
        if (cmdLine == null) {
            return -1;
        }

        if (cmdLine.getIsComment()) {
            return 0;
        }

        for (AbstractCommand curr : cmdLine.getCommands()) {
            final int returnCode = curr.work();
            /* Cut off execution once any command returned other than 0. */
            if (returnCode != 0) {
                return returnCode;
            }
        }
        return 0;
    }

    // Fails once indexToStartFrom is out of range
    // Returns -1 once failed
    protected int execute(AbstractCommandLine cmdLine, int indexToStartFrom) {
        /* Arguments check */
        if (cmdLine == null
                || indexToStartFrom < 0
                || indexToStartFrom >= cmdLine.getCommands().length) {
            return -1;
        }

        if (cmdLine.getIsComment()) {
            return 0;
        }

        for (int i = indexToStartFrom; i < cmdLine.getCommands().length; i ++) {
            final int returnCode = cmdLine.getCommands()[i].work();
            /* Cut off execution once any command returned other than 0. */
            if (returnCode != 0) {
                return returnCode;
            }
        }
        return 0;
    }
}
