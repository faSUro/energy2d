package org.concord.energy2d.commandmanagement.commands;

import org.concord.energy2d.system.System2D;

public class RunCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        try {
            if (args.equals("")) {
                if (!s2d.isRunning())
                    s2d.run();
                return "Simulation running...";
            } else {
                if (!s2d.isRunning()) {
                    long time = Long.parseLong(args);
                    s2d.run(time);
                    return "The simulation ran for " + time + " seconds.";
                } else
                    return "The simulation is already running.";

            }
        } catch (NumberFormatException e) {
            return "Wrong argument for \"run\" command. Use this format: run [{time}]";
        }
    }

}
