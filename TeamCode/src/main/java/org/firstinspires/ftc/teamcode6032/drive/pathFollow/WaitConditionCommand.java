package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.util.PollCallback;

@Deprecated
public class WaitConditionCommand extends PathCommand {
    private PollCallback<Boolean> callback;

    public WaitConditionCommand(PollCallback<Boolean> callbackIn) {
        callback = callbackIn;
    }


    @Override
    public boolean isComplete(PathFollower follower) {
        return callback.poll();
    }
}
