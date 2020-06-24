package org.concord.energy2d.commandmanagement;

import org.concord.energy2d.commandmanagement.commands.RunCommand;
import org.concord.energy2d.commandmanagement.commands.RunStepsCommand;
import org.concord.energy2d.commandmanagement.commands.StopCommand;
import org.concord.energy2d.system.System2D;

public class CommandProcessor {

    private System2D _s2d;
    private CommandExecutor cmdExecutor;

    public CommandProcessor(System2D s2d) {
        _s2d = s2d;
        cmdExecutor = new CommandExecutor(_s2d);
    }

    public String processCommand(String commandln) {
        String[] commandSplit = commandln.split(" ");
        String command = commandSplit[0].trim();
        String cmdArgs = "";
        if (commandln.length() > command.length())
            cmdArgs = commandln.substring(command.length() + 1);

        if (command.equalsIgnoreCase("help")) {
            return "help command response"; //TODO
        } else if (command.equalsIgnoreCase("run")) {
            return cmdExecutor.executeCommand(new RunCommand(), cmdArgs);
        } else if (command.equalsIgnoreCase("runsteps")) {
            return cmdExecutor.executeCommand(new RunStepsCommand(), cmdArgs);
        } else if (command.equalsIgnoreCase("stop")) {
            return cmdExecutor.executeCommand(new StopCommand(), cmdArgs);
        } else {
            return "Command not found.";
        }
    }

}
