package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.MechanamMotors;

public class RobotTargetMover {
    private static final boolean ORIGIN_IS_PREV_TARGET = true;
    private static final double LINE_ATTRACT_POW = 0; // 2x as powerful as target attraction.
    private static final double BRAKE_DIST = 16; // 16in
    private static final double BRAKE_DIST_R = 1; // 1rad (~120deg)
    public static final double TARGET_DIST = .5; // .5in
    public static final double TARGET_DIST_R = .075; // .075rad (~4deg)
    public static final double MAX_ACC = 5.0; // 5.0 speed unit/sec
    public static final double MAX_ACC_R = Math.PI*2; // 2pi rad/s^2

    private Pos target = null;
    private Pos vel = Pos.ORIGIN;
    private boolean brake = true;

    private Pos origin = null;

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
            mechanam.setPower(Pos.ORIGIN);
            return;
        }

        Pos pos = integrator.currentPos;
        Pos lineNormal = Pos.rot(Pos.normLoc(Pos.sub(origin,target)),Math.PI/2);
        // The direction to the line of travel.
        Pos toLine = Pos.project(lineNormal,Pos.sub(origin,pos));
        Pos toTarget = Pos.sub(target,pos);

        Pos moveDir = Pos.normMechanam(//Pos.add(
                Pos.mul(toTarget,1)//,
//                Pos.mul(toLine,LINE_ATTRACT_POW)
        );

        double distT = Pos.locLen(toTarget)-TARGET_DIST;
        double distR = Math.abs(toTarget.r)-TARGET_DIST_R;
        double speedT = brake ? Math.min( 1, distT/BRAKE_DIST) : 1;
        double speedR = brake ? Math.min( 1, distR/BRAKE_DIST_R) : 1;

        if (distT <= 0) speedT = 0;
        if (distR <= 0) speedR = 0;



        Pos newVel = Pos.mulComp(
                moveDir,
                new Pos(speedT,speedT,speedR)
        );
        Pos acc = Pos.mul(Pos.sub(newVel,vel),1.0/dt);
        acc = new Pos(
                Math.min(Math.max(acc.x,-MAX_ACC),MAX_ACC),
                Math.min(Math.max(acc.y,-MAX_ACC),MAX_ACC),
                Math.min(Math.max(acc.r,-MAX_ACC_R),MAX_ACC_R)
        );
        vel = Pos.add(vel,Pos.mul(acc,dt));


        mechanam.setPower(Pos.rot(Pos.minCutoff(vel,.075,.05),-pos.r,true));
    }


    public boolean isWithinDistanceToTarget(double targetDist, double targetDistR) {
        Pos pos = integrator.currentPos;
        Pos toTarget = Pos.sub(target,pos);
        double dist = Pos.locLen(toTarget), distR = toTarget.getRotCloseTo0();

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
    public void setTarget(Pos target, boolean brake) {
        updateOrigin();
        this.target = target;
        this.brake = brake;
    }
}
