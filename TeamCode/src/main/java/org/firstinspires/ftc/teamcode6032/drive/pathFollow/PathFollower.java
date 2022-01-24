package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.PosIntegrator;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;
import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

import java.util.ArrayList;
import java.util.List;

public class PathFollower {
    private double time;
    protected final RobotTargetMover targetMover;

    private List<PathCommand> commands = new ArrayList<>();
    private PathCommand currentCommand = null;

    public PathFollower(PosIntegrator integrator, MechanamMotors mechanam) {
        targetMover = new RobotTargetMover(integrator, mechanam);
    }

    public double getTime() { return time; }

    public void update(double time) {
        this.time = time;

        if (currentCommand == null) { // Init command if null, or throw if out of commands.
            if (commands.isEmpty())
                throw new IllegalStateException("Should not call PathFollower.update once completed");
            else nextCommand();
        }

        while (!commands.isEmpty() && currentCommand != null) { // Execute commands
            currentCommand.update(this);
            if (currentCommand.isComplete(this))
                nextCommand(); // Is complete, goto next command.
            else
                break; // If it's not complete yet, break and wait until the next.
        }
    }

    private void completeCommand() {
        if (currentCommand != null)
            currentCommand.onComplete(this);
        currentCommand = null;
    }

    private void nextCommand() {
        completeCommand();
        if (commands.isEmpty()) return;
        currentCommand = commands.get(0);
        commands.remove(0);
        currentCommand.start(this);
    }

    public void appendCommands(int n, List<PathCommand> commandsIn) {
        commands.addAll(n,commandsIn);
    }

    public void setCommands(List<PathCommand> commandsIn) {
        commands = commandsIn;
    }
    public boolean isComplete() {
        return commands.isEmpty();
    }

    public void assertPosition(Pos pos) {
        targetMover.integrator.setCurrentPos(pos);
    }
}
