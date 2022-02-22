package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DeadWheel {
    public final DcMotor motor;
    public final String motorId;
    public final double scale;

    public final boolean isReversed;

    public DeadWheel(DcMotor motorIn, String id, boolean reverse, double posScale) {
        motor = motorIn;
        motorId = id;
        scale = posScale;
        isReversed = reverse;
    }

    public int getPos() { return motor.getCurrentPosition()*(getIsReversed()!=isReversed?-1:1); }
    public double getPosScaled() { return getPos()*scale; }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }
}
