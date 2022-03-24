package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Vec;

@Deprecated
public class AssertPosCommand extends PathCommand {
    private final Vec pos;

    public AssertPosCommand(Vec pos) {
        this.pos = pos;
    }

    @Override
    boolean isComplete(PathFollower follower) {
        return true;
    }

    @Override
    void start(PathFollower follower) {
        follower.assertPosition(pos);
    }
}
