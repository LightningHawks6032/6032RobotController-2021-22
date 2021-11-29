package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class InitCommand extends PathCommand {
    private final Pos pos;
    public InitCommand(Pos posIn) {
        if (posIn.isNotPosition())
            throw new IllegalArgumentException("PathInitPosCommand(Pos posIn): posIn must be position");
        pos = posIn;
    }

    @Override
    boolean isComplete(PathFollower follower) { return true; }

    @Override
    void start(PathFollower follower) {
        follower.targetMover.integrator.setCurrentPos(pos);
    }
}
