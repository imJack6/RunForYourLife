package cn.jackcraft.runforyourlife.executions.theory;

import java.util.Arrays;

/**
 * @author William
 */
public class CommandSet {
    private AbstractCommand[] legalCommands;
    // If someone else modified "beenBlackListed" from certain AbstractCommand,
    // this array could validate it.
    // This array is the standard.
    private AbstractCommand[] blackList;

    private CommandSet() {
        this.legalCommands = new AbstractCommand[0];
        this.blackList = new AbstractCommand[0];
    }

    public CommandSet(AbstractCommand[] legalCommands) {
        this.legalCommands = legalCommands;
        this.blackList = new AbstractCommand[0];
    }

    public CommandSet(AbstractCommand[] legalCommands,
                      AbstractCommand[] blackList) {
        this.legalCommands = legalCommands;
        this.blackList = blackList;
    }

    public CommandSet(CommandSet previous, AbstractCommand[] legalCommands) {
        /* legalCommands */
        this.legalCommands = Arrays.copyOf(previous.legalCommands, legalCommands.length);
        /* Concatenate with newlyAdded into previous. */
        for (int i = previous.legalCommands.length - 1; i < this.legalCommands.length; i ++) {
            this.legalCommands[i] = legalCommands[i];
        }

        /* blackList */
        this.blackList = new AbstractCommand[0];
    }

    public CommandSet(CommandSet previous, AbstractCommand[] legalCommands,
                      AbstractCommand[] blackList) {
        /* legalCommands */
        this.legalCommands = Arrays.copyOf(previous.legalCommands, legalCommands.length);
        /* Concatenate with newlyAdded into previous. */
        for (int i = previous.legalCommands.length - 1; i < this.legalCommands.length; i ++) {
            this.legalCommands[i] = legalCommands[i];
        }

        /* blackList */
        this.blackList = Arrays.copyOf(previous.blackList, blackList.length);
        /* Concatenate with newlyAdded into previous. */
        for (int i = previous.blackList.length - 1; i < this.blackList.length; i ++) {
            this.blackList[i] = blackList[i];
        }
    }

    /* BlackListed commands will be ignored.
       Returns index of matched command in array legalCommand;
               -1 once not found.*/
    public int validation(AbstractCommand cmd) {
        for (int i = 0; i < this.legalCommands.length; i ++) {
            /* BlackListed, ignore. */
            if (this.legalCommands[i].isBeenBlackListed()) {
                continue;
            }
            if (cmd.equalsAs(this.legalCommands[i])) {
                return i;
            }
        }
        return -1;
    }

    /* BlackListed commands will be ignored.
       Returns index of matched command in array legalCommand;
               -1 once not found.*/
    public int validation(String identifyName) {
        for (int i = 0; i < this.legalCommands.length; i ++) {
            /* BlackListed, ignore. */
            if (this.legalCommands[i].isBeenBlackListed()) {
                continue;
            }
            if (identifyName.equals(this.legalCommands[i].getIdentifyName())) {
                return i;
            }
        }
        return -1;
    }

    protected void addToLegalCommands(AbstractCommand insertion) {
        this.legalCommands = Arrays.copyOf(this.legalCommands, this.legalCommands.length + 1);
        this.legalCommands[this.legalCommands.length - 1] = insertion;
    }

    protected void addToBlackList(AbstractCommand insertion) {
        this.blackList = Arrays.copyOf(this.blackList, this.blackList.length + 1);
        this.blackList[this.blackList.length - 1] = insertion;
    }

    public AbstractCommand getCommand(int index) {
        return this.legalCommands[index];
    }

}
