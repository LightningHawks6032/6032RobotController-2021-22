package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

public interface PathCommand {
    boolean isComplete(PathFollower follower);
    void start(PathFollower follower);
    void update(PathFollower follower);
}
