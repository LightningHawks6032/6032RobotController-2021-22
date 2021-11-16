package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

import java.util.function.Function;

public class RobotTargetMover {
    private static final boolean ORIGIN_IS_PREV_TARGET = true;
    private static final double LINE_ATTRACT_POW = 2; // 2x as powerful as target attraction.
    private static final double BRAKE_DIST = 6; // 6in
    private static final double BRAKE_DIST_R = 1; // 1rad (~60deg)
    private static final double TARGET_DIST = 1; // 6in
    private static final double TARGET_DIST_R = .2; // .2rad (~12deg)

    private Pos target = null;
    private boolean brake = true;

    private Pos origin = null;

    public final PosIntegrator integrator;
    public final MechanamMotors mechanam;
    private final Function<RobotTargetMover,Pos> targetGenerator;

    public RobotTargetMover(PosIntegrator integratorIn, MechanamMotors mechanamIn, Function<RobotTargetMover,Pos> targetGeneratorIn) {
        integrator = integratorIn;
        targetGenerator = targetGeneratorIn;
        mechanam = mechanamIn;
    }



    public void update() {
        if (target == null) {
            mechanam.setPower(Pos.STATIONARY);
            return;
        }

        Pos pos = integrator.currentPos;
        Pos lineNormal = Pos.rot(Pos.normLoc(Pos.sub(origin,target)),Math.PI/2);
        // The direction to the line of travel.
        Pos toLine = Pos.project(lineNormal,Pos.sub(origin,pos));
        Pos toTarget = Pos.sub(target,pos);

        Pos moveDir = Pos.normMechanam(Pos.add(
                Pos.mul(toTarget,1),
                Pos.mul(toLine,LINE_ATTRACT_POW)
        ));

        double dist = Pos.locLen(toTarget), distR = toTarget.r;
        double speed = brake
                ? Math.max(
                        (BRAKE_DIST-dist)/BRAKE_DIST,
                        (BRAKE_DIST_R-distR)/BRAKE_DIST_R )
                : 1;

        mechanam.setPower(Pos.mul(
                moveDir,
                speed,
                -1
        ));

        if (dist < TARGET_DIST && distR < TARGET_DIST_R)
            handleReachTarget();
    }



    private void handleReachTarget() {
        updateOrigin();
        target = targetGenerator.apply(this);
    }


    private void updateOrigin() {
        origin = target;
        target = null;
        if (origin == null || !ORIGIN_IS_PREV_TARGET)
            origin = integrator.currentPos;
    }
    public void setTarget(Pos target, boolean brake) {
        this.target = target;
        this.brake = brake;
    }
}
