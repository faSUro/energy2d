package org.concord.energy2d.commandline.command;

import org.concord.energy2d.system.System2D;

public class ResetCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        s2d.reset();
        return "Simulation reset.";
    }

}
