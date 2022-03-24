package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.drive.Vec;
import org.firstinspires.ftc.teamcode6032.drive.RobotTargetMover;

@Deprecated
public class TargetCommand extends PathCommand {
    private final Vec target;
    private final boolean brake;
    public final double targetDist;
    public final double targetDistR;

    public TargetCommand(Vec targetIn, boolean brakeIn, double targetDistIn, double targetDistRIn) {
        target = targetIn;
        targetDist = targetDistIn;
        targetDistR = targetDistRIn;
        brake = brakeIn;
    }
    public TargetCommand(Vec target, boolean brake, double targetDistScale) {
        this(
                target,
                brake,
                RobotTargetMover.TARGET_DIST*targetDistScale,
                RobotTargetMover.TARGET_DIST_R*targetDistScale
        );
    }
    public TargetCommand(Vec target) {
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

    @Override
    void update(PathFollower follower) {
        follower.targetMover.update();
    }
}
