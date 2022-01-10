package org.firstinspires.ftc.teamcode6032.drive.pathFollow;

import org.firstinspires.ftc.teamcode6032.util.PollCallback;

import java.util.Arrays;

public class BranchCommand extends PathCommand {
    private final PathCommand[][] pathOptions;
    private final PollCallback<CallbackOut> callback;

    public BranchCommand(PollCallback<CallbackOut> callbackIn, PathCommand[] ...pathOptionsIn) {
        callback = callbackIn;
        pathOptions = pathOptionsIn;
    }

    @Override
    boolean isComplete(PathFollower follower) {
        return true;
    }

    @Override
    void start(PathFollower follower) {
        CallbackOut res = callback.poll();
        follower.appendCommands(res.insertPos, Arrays.asList(pathOptions[res.branch]));
    }

    public static class CallbackIn {
        private CallbackIn() {}
    }
    public static class CallbackOut {
        public final int insertPos;
        public final int branch;
        private CallbackOut(int branch, int insertPos) {
            this.branch = branch;
            this.insertPos = insertPos;
        }
    }
    private static CallbackIn callbackIn() {
        return new CallbackIn();
    }
    public static CallbackOut callbackOut(int branch) {
        return callbackOut(branch,0);
    }
    public static CallbackOut callbackOut(int branch, int insertPos) {
        return new CallbackOut(branch, insertPos);
    }
}
