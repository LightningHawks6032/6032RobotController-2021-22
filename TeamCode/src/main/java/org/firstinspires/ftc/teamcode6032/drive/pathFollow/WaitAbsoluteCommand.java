package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

@Deprecated
public class WaitAbsoluteCommand extends PathCommand {
    private final double endTime;

    public WaitAbsoluteCommand(double time) {
        endTime = time;
    }

    @Override
    public boolean isComplete(PathFollower follower) {
        return follower.getTime() > endTime;
    }
}
