package org.firstinspires.ftc.teamcode6032.drive;

import org.firstinspires.ftc.teamcode6032.hardware.OdometryWheels;

public class PosIntegrator {
    public Pos currentPos = Pos.ORIGIN;
    public OdometryWheels wheels;

    private void addDelta(Pos delta) {
        if (!delta.isPosition())
            throw new IllegalArgumentException("PosIntegrator.addDelta received time-scaled argument");
        currentPos = Pos.add(currentPos,Pos.rot(delta, currentPos.r, true));
    }

    public PosIntegrator(OdometryWheels wheelsIn) {
        wheels = wheelsIn;
    }

    public void updatePos() {
        addDelta(wheels.getDeltaPos());
    }



}
