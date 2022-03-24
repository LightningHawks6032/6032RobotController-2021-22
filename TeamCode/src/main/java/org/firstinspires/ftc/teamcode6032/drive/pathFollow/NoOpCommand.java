package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

@Deprecated
public class NoOpCommand extends PathCommand {
    @Override
    boolean isComplete(PathFollower follower) {
        return true;
    }
}
