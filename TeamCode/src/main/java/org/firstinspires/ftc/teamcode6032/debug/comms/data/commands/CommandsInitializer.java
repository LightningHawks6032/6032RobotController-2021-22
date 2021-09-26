package org.firstinspires.ftc.teamcode6032.debug.comms.data.commands;

import static org.firstinspires.ftc.teamcode6032.debug.comms.data.Command.create;

public class CommandsInitializer {
    /** Load (or reload) all the commands */
    public static void load() {

        // Control commands, no function, but used in command sender.
        create("WAIT", p->null);
        create("FAIL",p->null);

        // Functional commands, handling or requesting data from the robot.
        create(new LogsCommand());
        //// TODO: add commands
    }
}
