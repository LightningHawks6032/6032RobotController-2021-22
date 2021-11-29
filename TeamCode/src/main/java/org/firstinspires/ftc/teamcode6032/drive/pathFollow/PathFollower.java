package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

public class PathFollower {
    private double time;
    protected final RobotTargetMover targetMover;

    private PathCommand[] commands = new PathCommand[0];
    private int commandIndex = -1;

    public PathFollower(PosIntegrator integrator, MechanamMotors mechanam) {
        targetMover = new RobotTargetMover(integrator, mechanam);
    }

    public double getTime() { return time; }

    public void update(double time) {
        this.time = time;

        if (commandIndex == -1) nextCommand();
        if (commandIndex >= commands.length) return;

        PathCommand current = commands[commandIndex];
        current.update(this);
        if (current.isComplete(this))
            nextCommand();
    }

    private void nextCommand() {
        if (commandIndex != -1)
            commands[commandIndex].onComplete(this);

        commandIndex++;
        if (commandIndex >= commands.length) return;

        commands[commandIndex].start(this);
    }


    public void setCommands(PathCommand[] commandsIn) {
        commands = commandsIn;
        commandIndex = 0;
    }

}
