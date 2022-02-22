package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.OdometryWheels;

public class PosIntegrator {
    public Pos currentPos = Pos.ORIGIN;
    public OdometryWheels wheels;

    private void addDelta(Pos delta) {
        Pos d = new Pos(delta.x,delta.y,delta.r);
        currentPos = Pos.add(currentPos,Pos.rot(d, currentPos.r, true));
//        currentPos = new Pos(currentPos.x+d.x,currentPos.y+d.y,currentPos.r+d.r);
    }

    public PosIntegrator(OdometryWheels wheelsIn) {
        wheels = wheelsIn;
    }

    public void updatePos() {
        addDelta(wheels.getDeltaPos());
//        Pos dp = wheels.getDeltaPos();
    }


    public void setCurrentPos(Pos currentPos) {
        this.currentPos = currentPos;
        wheels.getDeltaPos();
    }
}
