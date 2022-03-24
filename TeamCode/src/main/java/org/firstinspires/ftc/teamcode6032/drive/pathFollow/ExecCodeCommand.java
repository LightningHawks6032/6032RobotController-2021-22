package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

@Deprecated
public class ExecCodeCommand extends PathCommand {
    Runnable callback;
    public ExecCodeCommand(Runnable callbackIn) {
        callback = callbackIn;
    }
    @Override
    boolean isComplete(PathFollower follower) {
        return true;
    }

    @Override
    void start(PathFollower follower) {
        callback.run();
    }
}
