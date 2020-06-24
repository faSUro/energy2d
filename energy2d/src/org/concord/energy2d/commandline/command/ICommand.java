package org.concord.energy2d.commandline.command;

import org.concord.energy2d.system.System2D;

public interface ICommand {

    String execute(System2D s2d, String arg);

}
