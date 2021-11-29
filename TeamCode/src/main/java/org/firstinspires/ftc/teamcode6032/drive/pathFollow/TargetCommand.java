package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Pos;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;

public class TargetCommand extends PathCommand {
    private final Pos target;
    private final boolean brake;
    public final double targetDist;
    public final double targetDistR;

    public TargetCommand(Pos targetIn, boolean brakeIn, double targetDistIn, double targetDistRIn) {
        if (targetIn.isNotPosition())
            throw new IllegalArgumentException("Path target Pos was not a position.");
        target = targetIn;
        targetDist = targetDistIn;
        targetDistR = targetDistRIn;
        brake = brakeIn;
    }
    public TargetCommand(Pos target, boolean brake, double targetDistScale) {
        this(
                target,
                brake,
                RobotTargetMover.TARGET_DIST*targetDistScale,
                RobotTargetMover.TARGET_DIST_R*targetDistScale
        );
    }
    public TargetCommand(Pos target) {
        this(target,true,1);
    }

    @Override
    public boolean isComplete(PathFollower follower) {
        return follower.targetMover.isWithinDistanceToTarget();
    }

    @Override
    public void start(PathFollower follower) {
        follower.targetMover.setTarget(target,brake);
    }
}
