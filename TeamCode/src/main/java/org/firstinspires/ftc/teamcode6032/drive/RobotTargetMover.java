package org.firstinspires.ftc.teamcode6032.drive;

import androidx.core.math.MathUtils;

import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.MechanamMotors;

public class RobotTargetMover {
    private static final boolean ORIGIN_IS_PREV_TARGET = true;
    private static final double BRAKE_DIST = 20; // 1ft 8 in
    private static final double BRAKE_DIST_R = 1; // 1rad (~120deg)
    public static final double TARGET_DIST = 1; // 1in
    public static final double TARGET_DIST_R = .15; // .15rad (~8deg)
    public static final double MIN_POW = 0.1; // 0.1 speed
    public static final double MIN_POW_R = 0.1; // 0.1 r-speed
    public static final double MIN_POW_DIST = 0.5; // 0.5x TARGET_DIST
    public static final double MIN_POW_DIST_R = 0.5; // 0.5x TARGET_DIST_R
    public static final double MAX_ACC = 4.0; // 4.0 speed/sec
    public static final double MAX_ACC_R = Math.PI*2; // 2pi rad/s^2

    private Vec target = null;
    private Vec vel = Vec.ZERO;
    private boolean brake = true;

    private Vec origin = null;

    public final PosIntegrator integrator;
    public final MechanamMotors mechanam;

    public RobotTargetMover(PosIntegrator integratorIn, MechanamMotors mechanamIn) {
        integrator = integratorIn;
        mechanam = mechanamIn;
    }


    @Deprecated
    public void update() {
        update(0);
    }
    public void update(double dt) {
        if (target == null) {
            mechanam.setPower(Vec.ZERO);
            return;
        }

        Vec pos = integrator.currentPos;
        Vec lineNormal = Vec.rot(Vec.normLoc(Vec.sub(origin,target)),Math.PI/2);
        // The direction to the line of travel.
        Vec toLine = Vec.project(lineNormal, Vec.sub(origin,pos));
        Vec toTarget = Vec.sub(target,pos);

        Vec moveDir = Vec.normMechanam(//Pos.add(
                Vec.mul(toTarget,1)//,
//                Pos.mul(toLine,LINE_ATTRACT_POW)
        );

//        double distT = Vec.locLen(toTarget)-TARGET_DIST;
//        double distR = Math.abs(toTarget.r)-TARGET_DIST_R;
        double distT = Vec.locLen(toTarget) - TARGET_DIST*MIN_POW_DIST;
        double distR = Math.abs(toTarget.r) - TARGET_DIST_R*MIN_POW_DIST_R;
        double speedT = brake ? applyMinPow(distT/BRAKE_DIST, MIN_POW) : 1;
        double speedR = brake ? applyMinPow(distR/BRAKE_DIST_R, MIN_POW_R) : 1;

        if (distT <= 0) speedT = 0;
        if (distR <= 0) speedR = 0;



        Vec newVel = Vec.mulComp(
                moveDir,
                new Vec(speedT,speedT,speedR)
        );
        Vec acc = Vec.mul(Vec.sub(newVel,vel),1.0/dt);
        acc = new Vec(
                MathUtils.clamp(acc.x,-MAX_ACC,MAX_ACC),
                MathUtils.clamp(acc.y,-MAX_ACC,MAX_ACC),
                MathUtils.clamp(acc.r,-MAX_ACC_R,MAX_ACC_R)
        );
        vel = Vec.add(vel, Vec.mul(acc,dt));


        mechanam.setPower(Vec.rot(Vec.minCutoff(vel,.075,.05),-pos.r,true));
    }

    private double applyMinPow(double pow, double minPow) {
        if (pow <= 0) return 0;
        else return MathUtils.clamp(pow+minPow,MIN_POW,1);
    }

    public boolean isWithinDistanceToTarget(double targetDist, double targetDistR) {
        Vec pos = integrator.currentPos;
        Vec toTarget = Vec.sub(target,pos);
        double dist = Vec.locLen(toTarget), distR = toTarget.getRotCloseTo0();

        System.out.println("Dist: "+dist+", DistR: "+distR);

        return dist < targetDist && distR < Math.abs(targetDistR);
    }
    public boolean isWithinDistanceToTarget() {
        return isWithinDistanceToTarget(TARGET_DIST, TARGET_DIST_R);
    }

    public void updateOrigin() {
        origin = target;
        target = null;
        if (origin == null || !ORIGIN_IS_PREV_TARGET)
            origin = integrator.currentPos;
    }
    public void setTarget(Vec target, boolean brake) {
        updateOrigin();
        this.target = target;
        this.brake = brake;
    }
}
