package org.firstinspires.ftc.teamcode6032.drive.auto;

import org.firstinspires.ftc.teamcode6032.drive.Vec;

/**
 * The main auto code shortcuts class
 */
public class Aut {

    ///// ＿＿＿＿＿ / DRIVE CONTROL CODE / ＿＿＿＿＿＿ /////

    // -- [de]activation -- //

    public static void driveStart(AutoContext X) {
        X.runner.addLoopActivity(Aut::posTargetingLoopActivity);
    }
    public static void driveStop(AutoContext X) {
        X.runner.removeLoopActivity(Aut::posTargetingLoopActivity);
        X.hardware.mechanam.setPower(new Vec(0,0,0));
    }

    // -- positioning -- //

    public static void goTo(AutoContext X, Vec pos) {
        goTo(X,pos,true);
    }
    public static void goTo(AutoContext X, Vec pos, boolean brake) {
        X.hardware.targetMover.setTarget(pos,brake);
        X.await(X.hardware.targetMover::isWithinDistanceToTarget);
    }
    public static void assertPos(AutoContext X, Vec pos) {
        X.hardware.posIntegrator.setCurrentPos(pos);
    }

    // -- loop activities -- //

    public static void posTargetingLoopActivity(AutoRunner R, double dt) {
        R.hardware.targetMover.update(dt);
        R.hardware.posIntegrator.updatePos();
    }


    ///// ＿＿＿＿＿ / TIMING AND WAITING HELPERS / ＿＿＿＿＿＿ /////

    public static void wait(AutoContext X, double delay) {
        double now = X.hardware.time();
        waitAbsolute(X,now + delay);
    }
    public static void waitAbsolute(AutoContext X, double time) {
        X.await(()->(X.hardware.time() > time));
    }

    public static void waitNLoops(AutoContext X, int loops) {
        X.await(new AwaitConditionFn() {
            int n = 0;
            @Override public boolean canContinue() {
                return n++ > loops;
            }
        });
    }
    public static void waitOneLoop(AutoContext X) {
        waitNLoops(X,1);
    }

}
