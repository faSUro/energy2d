package org.concord.energy2d.commandmanagement;

import org.concord.energy2d.commandmanagement.commands.ICommand;
import org.concord.energy2d.system.System2D;

public class CommandExecutor {

    private System2D _s2d;

    public CommandExecutor(System2D s2d) {
        _s2d = s2d;
    }

    public String executeCommand(ICommand command, String arg) {
        return command.execute(_s2d, arg);
    }

}
