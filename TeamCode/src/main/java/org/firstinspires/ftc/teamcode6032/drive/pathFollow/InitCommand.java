package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Vec;

@Deprecated
public class InitCommand extends PathCommand {
    private final Vec pos;
    public InitCommand(Vec posIn) {
        pos = posIn;
    }

    @Override
    boolean isComplete(PathFollower follower) { return true; }

    @Override
    void start(PathFollower follower) {
        follower.targetMover.integrator.setCurrentPos(pos);
    }
}
