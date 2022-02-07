package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.OdometryWheels;

public class PosIntegrator {
    public Pos currentPos = Pos.ORIGIN;
    public OdometryWheels wheels;

    private void addDelta(Pos delta) {
        currentPos = Pos.add(currentPos,Pos.rot(delta, currentPos.r, true));
    }

    public PosIntegrator(OdometryWheels wheelsIn) {
        wheels = wheelsIn;
    }

    public void updatePos() {
        addDelta(wheels.getDeltaPos());
    }


    public void setCurrentPos(Pos currentPos) {
        this.currentPos = currentPos;
        wheels.getDeltaPos();
    }
}
