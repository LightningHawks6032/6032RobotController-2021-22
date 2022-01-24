package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Pos;

public class AssertPosCommand extends PathCommand {
    private final Pos pos;

    public AssertPosCommand(Pos pos) {
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
