package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

public class PathWaitCommand implements PathCommand {
    private double startTime;
    private final double delay;

    public PathWaitCommand(double delayIn) {
        delay = delayIn;
    }


    @Override
    public boolean isComplete(PathFollower follower) {
        return follower.getTime() > startTime + delay;
    }

    @Override
    public void start(PathFollower follower) {
        startTime = follower.getTime();
    }

    @Override
    public void update(PathFollower follower) { } // nothing needs to be updated
}
