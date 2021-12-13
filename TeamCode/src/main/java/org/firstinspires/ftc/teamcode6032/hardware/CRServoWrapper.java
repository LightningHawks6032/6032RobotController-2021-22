package org.firstinspires.ftc.teamcode6032.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class CRServoWrapper implements IMotorInfo {
    public final CRServo motor;
    public final String motorId;

    public CRServoWrapper(CRServo motorIn, String id, boolean reverse) {
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
    public double getPos() { return 0; }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }

    @Override
    public MotorInfoType getType() { return MotorInfoType.DC_MOTOR; }
}
