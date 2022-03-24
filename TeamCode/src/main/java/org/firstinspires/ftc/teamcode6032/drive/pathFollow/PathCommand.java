package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

@Deprecated
public abstract class PathCommand {
    abstract boolean isComplete(PathFollower follower);
    void start(PathFollower follower) {}
    void update(PathFollower follower) {}
    void onComplete(PathFollower follower) {}
    void cancel(PathFollower follower) { onComplete(follower); }
}
