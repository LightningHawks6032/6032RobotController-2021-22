package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class InitCommand extends PathCommand {
    private final Pos pos;
    public InitCommand(Pos posIn) {
        pos = posIn;
    }

    @Override
    boolean isComplete(PathFollower follower) { return true; }

    @Override
    void start(PathFollower follower) {
        follower.targetMover.integrator.setCurrentPos(pos);
    }
}
