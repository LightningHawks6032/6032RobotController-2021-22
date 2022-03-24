package org.firstinspires.ftc.teamcode6032.hardware.wrapper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode6032.hardware.IMotorInfo;
import org.firstinspires.ftc.teamcode6032.hardware.MotorInfoType;

public class DeadWheelWrapper implements IMotorInfo {
    public final DcMotor motor;
    public final String motorId;
    public final double scale;

    public final boolean isReversed;

    public DeadWheelWrapper(DcMotor motorIn, String id, boolean reverse, double posScale) {
        motor = motorIn;
        motorId = id;
        scale = posScale;
        isReversed = reverse;
    }


    public int getPosTicks() { return motor.getCurrentPosition()*(getIsReversed()!=isReversed?-1:1); }
    public double getPos() { return getPosTicks()*scale; }
    public boolean getIsReversed() { return motor.getDirection() == DcMotorSimple.Direction.REVERSE; }
    public String getMotorId() { return motorId; }

    public double getPower() { return 0; }
    public MotorInfoType getType() { return MotorInfoType.DEAD_WHEEL; }
}
