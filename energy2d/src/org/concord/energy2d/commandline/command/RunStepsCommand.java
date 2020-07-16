package org.concord.energy2d.commandline.command;

import org.concord.energy2d.system.System2D;

public class RunStepsCommand implements ICommand {

    @Override
    public String execute(System2D s2d, String args) {
        try {
            int steps = Integer.parseInt(args);
            s2d.runSteps(steps);
            return "Simulation ran " + steps + " steps.";
        } catch (NumberFormatException e) {
            return "Wrong argument for \"runsteps\" command. Use this format: runsteps {steps}";
        }
    }

}
