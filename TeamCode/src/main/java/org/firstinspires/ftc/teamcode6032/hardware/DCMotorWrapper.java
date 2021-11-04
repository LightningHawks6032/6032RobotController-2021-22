package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DCMotorWrapper implements IMotorInfo {
    public final DcMotor motor;
    public final String motorId;

    public DCMotorWrapper(DcMotor motorIn, String id, boolean reverse) {
        motor = motorIn;
        motorId = id;
        motor.setDirection(reverse ?
                DcMotorSimple.Direction.REVERSE :
                DcMotorSimple.Direction.FORWARD
        );
    }


    public void setPower(double power) {
        motor.setPower(power);
    }

    public double getPower() { return motor.getPower(); }
    public int getPos() { return motor.getCurrentPosition(); }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }
}
