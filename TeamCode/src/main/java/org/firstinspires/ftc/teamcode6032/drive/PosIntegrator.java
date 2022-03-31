package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.subassembelyControl.core.OdometryWheels;

public class PosIntegrator {

    // [unscaled] `expected / actual`
//    public Pos ODO_DELTA_SCALE = new Pos(24.0/27.3,24.0/23.9,10.0/10.3);//TODO
    public Vec ODO_DELTA_SCALE = new Vec(1,1,10.0/10.3);//TODO

    public Vec currentPos = Vec.ZERO;
    public OdometryWheels wheels;

    private void addDelta(Vec delta) {
        Vec d = new Vec(delta.x,delta.y,delta.r);
        currentPos = Vec.add(currentPos, Vec.rot(d, currentPos.r, true));
//        currentPos = new Pos(currentPos.x+d.x,currentPos.y+d.y,currentPos.r+d.r);
    }

    public PosIntegrator(OdometryWheels wheelsIn) {
        wheels = wheelsIn;
    }

    public void updatePos() {
        addDelta(Vec.mulComp(wheels.getDeltaPos(), ODO_DELTA_SCALE));
//        Pos dp = wheels.getDeltaPos();
    }


    public void setCurrentPos(Vec currentPos) {
        this.currentPos = currentPos;
        wheels.getDeltaPos();
    }
}
