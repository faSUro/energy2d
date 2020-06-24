package org.concord.energy2d.commandmanagement.commands;

import org.concord.energy2d.system.System2D;

public class StopCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        if (s2d.isRunning())
            s2d.stop();
        return "Simulation stopped.";
    }

}
