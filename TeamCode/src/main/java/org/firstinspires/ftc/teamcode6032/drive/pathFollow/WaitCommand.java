package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

public class WaitCommand extends PathCommand {
    private double startTime;
    private final double delay;

    public WaitCommand(double delayIn) {
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
}
