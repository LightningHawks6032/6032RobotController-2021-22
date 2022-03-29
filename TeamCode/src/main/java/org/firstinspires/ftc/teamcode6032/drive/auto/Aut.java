package org.firstinspires.ftc.teamcode6032.drive.auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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

    public static void goTo(AutoContext X, Vec pos) throws InterruptedException {
        goTo(X,pos,true);
    }
    public static void goTo(AutoContext X, Vec pos, boolean brake) throws InterruptedException {
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

    public static void wait(AutoContext X, double delay) throws InterruptedException {
        double now = X.hardware.time();
        waitAbsolute(X,now + delay);
    }
    public static void waitAbsolute(AutoContext X, double time) throws InterruptedException {
        X.await(()->(X.hardware.time() > time));
    }

    public static void waitNLoops(AutoContext X, int loops) throws InterruptedException {
        X.await(new AwaitConditionFn() {
            int n = 0;
            @Override public boolean canContinue() {
                return n++ > loops;
            }
        });
    }
    public static void waitOneLoop(AutoContext X) throws InterruptedException {
        waitNLoops(X,1);
    }


    ///// ＿＿＿＿＿ / Telemetry and Logging / ＿＿＿＿＿＿ /////

    public static void setLine(AutoContext X, String ln) {
        Telemetry tele = X.hardware.telemetry;
        tele.addLine(ln);
        tele.update();
    }
}
