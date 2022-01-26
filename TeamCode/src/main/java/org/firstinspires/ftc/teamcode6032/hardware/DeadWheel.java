package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DeadWheel {
    public final DcMotor motor;
    public final String motorId;
    public final double scale;

    public DeadWheel(DcMotor motorIn, String id, boolean reverse, double posScale) {
        motor = motorIn;
        motorId = id;
        motor.setDirection(reverse ?
                DcMotorSimple.Direction.REVERSE :
                DcMotorSimple.Direction.FORWARD
        );
        scale = posScale;
    }

    public int getPos() { return motor.getCurrentPosition(); }
    public double getPosScaled() { return getPos()*scale; }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }
}
